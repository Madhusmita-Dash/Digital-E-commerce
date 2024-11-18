package com.digitalecommerce.digital.e_commerce.controller;

import com.digitalecommerce.digital.e_commerce.dto.AuthenticationRequest;
import com.digitalecommerce.digital.e_commerce.dto.SignupRequest;
import com.digitalecommerce.digital.e_commerce.dto.UserDto;
import com.digitalecommerce.digital.e_commerce.entity.AppUser;
import com.digitalecommerce.digital.e_commerce.repository.UserRepository;
import com.digitalecommerce.digital.e_commerce.services.jwt.Auth.AuthService;
import com.digitalecommerce.digital.e_commerce.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class Authenticate {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    private final AuthService authService;

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            // Return an error message if credentials are invalid
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid username or password");
            return;
        }

        // Load user details from the database
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<AppUser> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

        // Generate JWT token
        final String token = jwtUtil.generateToken(userDetails.getUsername());

        if (optionalUser.isPresent()) {
            // Write user details and token to response if user is found
            response.getWriter().write(
                    new JSONObject()
                            .put("UserId", optionalUser.get().getId())
                            .put("role", optionalUser.get().getRole())
                            .put("token", TOKEN_PREFIX + token)
                            .toString()
            );

            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
            response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        // Check if user already exists
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        }

        // Create new user
        UserDto userDto = authService.createUser(signupRequest);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}


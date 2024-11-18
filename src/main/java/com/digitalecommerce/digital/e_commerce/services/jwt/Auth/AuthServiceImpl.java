package com.digitalecommerce.digital.e_commerce.services.jwt.Auth;

import com.digitalecommerce.digital.e_commerce.dto.SignupRequest;
import com.digitalecommerce.digital.e_commerce.dto.UserDto;
import com.digitalecommerce.digital.e_commerce.entity.AppUser;
import com.digitalecommerce.digital.e_commerce.enums.UserRole;
import com.digitalecommerce.digital.e_commerce.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto createUser(SignupRequest signupRequest) {
        AppUser user = new AppUser();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);

        AppUser createdUser = userRepository.save(user);

        // Set UserDto properties based on the created user
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());

        // Return the created UserDto
        return userDto;
    }

    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void createAdminAccount() {
        Optional<AppUser> adminAccountOptional = userRepository.findByRole(UserRole.ADMIN);

        if (!adminAccountOptional.isPresent()) {
            AppUser user = new AppUser();
            user.setEmail("admin@email.com");
            user.setName("admin");
            user.setRole(UserRole.ADMIN);
            user.setPassword(passwordEncoder.encode("admin"));
            userRepository.save(user);
        }
    }
}

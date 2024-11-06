package com.digitalecommerce.digital.e_commerce.services.jwt;

import com.digitalecommerce.digital.e_commerce.repository.UserRepository;
import com.digitalecommerce.digital.e_commerce.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> optionalUser = userRepository.findFirstByEmail(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }

        AppUser user = optionalUser.get();
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>() // Add roles or authorities if needed
        );
    }
}

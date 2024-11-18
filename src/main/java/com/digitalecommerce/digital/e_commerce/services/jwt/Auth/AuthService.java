package com.digitalecommerce.digital.e_commerce.services.jwt.Auth;

import com.digitalecommerce.digital.e_commerce.dto.SignupRequest;
import com.digitalecommerce.digital.e_commerce.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
    Boolean hasUserWithEmail(String email);
}

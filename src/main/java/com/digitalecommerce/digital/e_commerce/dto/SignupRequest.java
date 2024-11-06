package com.digitalecommerce.digital.e_commerce.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;

    private String password;

    private String name;
}

package com.digitalecommerce.digital.e_commerce.entity;

import com.digitalecommerce.digital.e_commerce.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import java.lang.*;

@Entity
@Data
@Table(name="users")

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String name;

    private UserRole role;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[]img;
}

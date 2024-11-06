package com.digitalecommerce.digital.e_commerce.repository;

import com.digitalecommerce.digital.e_commerce.entity.AppUser;
import com.digitalecommerce.digital.e_commerce.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    // Custom query method to find an AppUser by email
    Optional<AppUser> findFirstByEmail(String email);

    Optional<AppUser> findByRole(UserRole role);


}

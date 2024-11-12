package com.digitalecommerce.digital.e_commerce.repository;


import com.digitalecommerce.digital.e_commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
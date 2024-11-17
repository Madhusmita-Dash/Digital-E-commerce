package com.digitalecommerce.digital.e_commerce.repository;


import com.digitalecommerce.digital.e_commerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}


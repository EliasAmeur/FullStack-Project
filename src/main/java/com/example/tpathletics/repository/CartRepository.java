package com.example.tpathletics.repository;

import com.example.tpathletics.entity.Cart;
import com.example.tpathletics.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}

package com.masudmirza.trade_platform.repository;

import com.masudmirza.trade_platform.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}

package com.masudmirza.trade_platform.repository;

import com.masudmirza.trade_platform.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}

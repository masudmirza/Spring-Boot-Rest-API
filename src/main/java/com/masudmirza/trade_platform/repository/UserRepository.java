package com.masudmirza.trade_platform.repository;

import com.masudmirza.trade_platform.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.deletedAt IS NULL")
    Optional<User> findNonDeletedById(UUID id);

    @Query("SELECT u FROM User u WHERE u.deletedAt IS NULL " +
            "AND (:name IS NULL OR LOWER(u.name) LIKE %:name%) " +
            "AND (:email IS NULL OR LOWER(u.email) LIKE %:email%) " +
            "AND (:surname IS NULL OR LOWER(u.surname) LIKE %:surname%) " +
            "AND (:phoneNumber IS NULL OR LOWER(u.phoneNumber) LIKE %:phoneNumber%)")
    Page<User> findAllUsers(
            @Param("name") String name,
            @Param("email") String email,
            @Param("surname") String surname,
            @Param("phoneNumber") String phoneNumber,
            Pageable pageable);

    @Query("SELECT COUNT(u) FROM User u WHERE u.deletedAt IS NULL " +
            "AND (:name IS NULL OR LOWER(u.name) LIKE %:name%) " +
            "AND (:email IS NULL OR LOWER(u.email) LIKE %:email%) " +
            "AND (:surname IS NULL OR LOWER(u.surname) LIKE %:surname%) " +
            "AND (:phoneNumber IS NULL OR LOWER(u.phoneNumber) LIKE %:phoneNumber%)")
    long countAllUsers(
            @Param("name") String name,
            @Param("email") String email,
            @Param("surname") String surname,
            @Param("phoneNumber") String phoneNumber);
}

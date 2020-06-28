package com.microservice.forexexchange.repositories;
import java.util.Optional;

import com.microservice.forexexchange.models.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    Boolean existsByUsername(String username);
    Optional<Admin> findByUsername(String username);
}
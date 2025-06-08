package com.example.dispatch.repository;

import com.example.dispatch.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {  // 改為 Integer
    Optional<AppUser> findByUsername(String username);
    boolean existsByUsername(String username);
}

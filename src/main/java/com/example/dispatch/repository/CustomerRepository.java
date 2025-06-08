package com.example.dispatch.repository;

import com.example.dispatch.model.AppUser;
import com.example.dispatch.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {  // 改為 Integer
    List<Customer> findByCreatedBy(AppUser createdBy);
    List<Customer> findByNameContainingIgnoreCase(String name);
}

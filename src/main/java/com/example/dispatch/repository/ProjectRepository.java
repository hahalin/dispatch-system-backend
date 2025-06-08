package com.example.dispatch.repository;

import com.example.dispatch.model.AppUser;
import com.example.dispatch.model.Customer;
import com.example.dispatch.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {  // 改為 Integer
    List<Project> findByCreatedBy(AppUser createdBy);
    List<Project> findByCustomer(Customer customer);
    List<Project> findByNameContainingIgnoreCase(String name);
}

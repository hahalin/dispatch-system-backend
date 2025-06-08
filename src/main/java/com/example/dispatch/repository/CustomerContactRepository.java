package com.example.dispatch.repository;

import com.example.dispatch.model.CustomerContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerContactRepository extends JpaRepository<CustomerContact, Long> {
    List<CustomerContact> findByCustomer_Id(Long customerId);
}

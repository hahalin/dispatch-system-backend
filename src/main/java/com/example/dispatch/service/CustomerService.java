package com.example.dispatch.service;

import com.example.dispatch.dto.CustomerDTO;
import com.example.dispatch.exception.ResourceNotFoundException;
import com.example.dispatch.model.AppUser;
import com.example.dispatch.model.Customer;
import com.example.dispatch.repository.CustomerRepository;
import com.example.dispatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CustomerDTO> getAllCustomers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Customer> customers;
        if ("ADMIN".equals(user.getRole())) {
            customers = customerRepository.findAll();
        } else {
            customers = customerRepository.findByCreatedBy(user);
        }

        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        // Check if user has permission
        checkPermission(customer);

        return convertToDTO(customer);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setIndustry(customerDTO.getIndustry());
        customer.setAddress(customerDTO.getAddress());
        customer.setCreatedBy(user);

        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    public CustomerDTO updateCustomer(Integer id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        // Check if user has permission
        checkPermission(customer);

        customer.setName(customerDTO.getName());
        customer.setIndustry(customerDTO.getIndustry());
        customer.setAddress(customerDTO.getAddress());

        Customer updatedCustomer = customerRepository.save(customer);
        return convertToDTO(updatedCustomer);
    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        // Check if user has permission
        checkPermission(customer);

        customerRepository.delete(customer);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setIndustry(customer.getIndustry());
        dto.setAddress(customer.getAddress());
        return dto;
    }

    private void checkPermission(Customer customer) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!"ADMIN".equals(user.getRole()) && !customer.getCreatedBy().getId().equals(user.getId())) {
            throw new RuntimeException("You don't have permission to access this customer");
        }
    }
}

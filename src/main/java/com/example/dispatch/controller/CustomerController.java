package com.example.dispatch.controller;

import com.example.dispatch.dto.CustomerDTO;
import com.example.dispatch.dto.MessageResponse;
import com.example.dispatch.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "客戶管理", description = "客戶資料管理相關的 API")
@SecurityRequirement(name = "JWT")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    @Operation(summary = "取得所有客戶", description = "取得系統中所有客戶的資料")
    @ApiResponse(responseCode = "200", description = "成功取得客戶列表", 
                content = @Content(schema = @Schema(implementation = CustomerDTO.class)))
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根據 ID 取得客戶", description = "根據指定的 ID 取得特定客戶的資料")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功取得客戶資料", 
                    content = @Content(schema = @Schema(implementation = CustomerDTO.class))),
        @ApiResponse(responseCode = "404", description = "找不到指定的客戶")
    })
    public ResponseEntity<CustomerDTO> getCustomerById(
            @Parameter(description = "客戶 ID", required = true) @PathVariable Integer id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    @Operation(summary = "新增客戶", description = "新增一位新的客戶")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "客戶新增成功", 
                    content = @Content(schema = @Schema(implementation = CustomerDTO.class))),
        @ApiResponse(responseCode = "400", description = "輸入資料不正確")
    })
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新客戶資料", description = "更新指定 ID 的客戶資料")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "客戶資料更新成功", 
                    content = @Content(schema = @Schema(implementation = CustomerDTO.class))),
        @ApiResponse(responseCode = "400", description = "輸入資料不正確"),
        @ApiResponse(responseCode = "404", description = "找不到指定的客戶")
    })
    public ResponseEntity<CustomerDTO> updateCustomer(
            @Parameter(description = "客戶 ID", required = true) @PathVariable Integer id, 
            @Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "刪除客戶", description = "刪除指定 ID 的客戶")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "客戶刪除成功", 
                    content = @Content(schema = @Schema(implementation = MessageResponse.class))),
        @ApiResponse(responseCode = "404", description = "找不到指定的客戶")
    })
    public ResponseEntity<MessageResponse> deleteCustomer(
            @Parameter(description = "客戶 ID", required = true) @PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(MessageResponse.success("Customer deleted successfully"));
    }
}

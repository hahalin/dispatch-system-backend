package com.example.dispatch;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Tag(name = "系統狀態", description = "系統基本狀態檢查")
public class DispatchSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DispatchSystemApplication.class, args);
    }
    
    @GetMapping("/")
    @Operation(summary = "系統健康檢查", description = "檢查派工系統 API 是否正常運作")
    @ApiResponse(responseCode = "200", description = "系統正常運作")
    public String home() {
        return "Dispatch System API is running";
    }
}

package com.example.dispatch.controller;

import com.example.dispatch.dto.JwtResponse;
import com.example.dispatch.dto.LoginRequest;
import com.example.dispatch.dto.MessageResponse;
import com.example.dispatch.dto.RegisterRequest;
import com.example.dispatch.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@Tag(name = "認證管理", description = "用戶認證相關的 API")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "用戶登入", description = "使用用戶名和密碼進行登入認證")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "登入成功", 
                    content = @Content(schema = @Schema(implementation = JwtResponse.class))),
        @ApiResponse(responseCode = "401", description = "認證失敗", 
                    content = @Content(schema = @Schema(implementation = MessageResponse.class)))
    })
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Login attempt for username: " + loginRequest.getUsername());
            JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
            System.out.println("Login successful for username: " + loginRequest.getUsername());
            return ResponseEntity.ok(jwtResponse);
        } catch (BadCredentialsException e) {
            System.out.println("Bad credentials for username: " + loginRequest.getUsername());
            return ResponseEntity.status(401).body(new MessageResponse("Invalid username or password", false));
        } catch (Exception e) {
            System.out.println("Login error for username: " + loginRequest.getUsername() + ", error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(401).body(new MessageResponse("Authentication failed", false));
        }
    }

    @PostMapping("/register")
    @Operation(summary = "用戶註冊", description = "註冊新用戶帳號")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "註冊成功", 
                    content = @Content(schema = @Schema(implementation = MessageResponse.class))),
        @ApiResponse(responseCode = "400", description = "註冊失敗", 
                    content = @Content(schema = @Schema(implementation = MessageResponse.class))),
        @ApiResponse(responseCode = "500", description = "伺服器錯誤", 
                    content = @Content(schema = @Schema(implementation = MessageResponse.class)))
    })
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            System.out.println("Register attempt for username: " + registerRequest.getUsername());
            MessageResponse response = authService.registerUser(registerRequest);
            if (response.isSuccess()) {
                System.out.println("Register successful for username: " + registerRequest.getUsername());
                return ResponseEntity.ok(response);
            } else {
                System.out.println("Register failed for username: " + registerRequest.getUsername() + ", reason: " + response.getMessage());
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            System.out.println("Register error for username: " + registerRequest.getUsername() + ", error: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(new MessageResponse("Registration failed", false));
        }
    }
}

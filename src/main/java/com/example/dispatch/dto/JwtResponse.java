package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;  // 改為 Integer
    private String username;
    private String name;
    private String role;
    
    public JwtResponse(String token, Integer id, String username, String name, String role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
    }
}

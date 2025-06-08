package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelProjectRequest {
    
    @NotNull(message = "Personnel ID cannot be null")
    private Long personnelId;
    
    @NotNull(message = "Project ID cannot be null")
    private Long projectId;
    
    private String title; // Title
    
    private String responsibilities; // Job responsibilities
    
    private LocalDate startDate;
    
    private LocalDate endDate;
}

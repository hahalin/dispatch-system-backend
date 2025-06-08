package com.example.dispatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "personnel_project")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelProject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // 改為 Integer 以匹配 SERIAL
    
    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    
    private String title; // Title
    
    private String responsibilities; // Job responsibilities
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

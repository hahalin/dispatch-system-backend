package com.example.dispatch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "project_responsibilities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponsibility {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "responsibility", columnDefinition = "TEXT")
    private String responsibility;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectExperience project;
} 
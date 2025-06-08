package com.example.dispatch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "project_technologies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechnology {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "category", length = 20)
    private String category; // 'frontend', 'backend', 'database', 'os', 'tools'
    
    @Column(name = "technology", length = 100)
    private String technology;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectExperience project;
} 
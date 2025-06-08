package com.example.dispatch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project_experience")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectExperience {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "project_name", nullable = false, length = 200)
    private String projectName;
    
    @Column(name = "client", length = 100)
    private String client;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "role", length = 100)
    private String role;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "team_size")
    private Integer teamSize;
    
    @Column(name = "evaluation")
    private Integer evaluation;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjectTechnology> technologies = new ArrayList<>();
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjectResponsibility> responsibilities = new ArrayList<>();
} 
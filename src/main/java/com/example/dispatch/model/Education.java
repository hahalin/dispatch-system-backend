package com.example.dispatch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "education")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "degree", length = 20)
    private String degree;
    
    @Column(name = "school", length = 100)
    private String school;
    
    @Column(name = "department", length = 100)
    private String department;
    
    @Column(name = "graduation_year")
    private Integer graduationYear;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;
} 
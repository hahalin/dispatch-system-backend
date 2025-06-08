package com.example.dispatch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "industry_experience")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndustryExperience {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "industry_name", nullable = false, length = 100)
    private String industryName;
    
    @Column(name = "years")
    private Integer years = 0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;
} 
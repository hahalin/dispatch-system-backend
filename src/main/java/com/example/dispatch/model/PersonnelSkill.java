package com.example.dispatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "personnel_skill")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelSkill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // 改為 Integer 以匹配 SERIAL
    
    @ManyToOne
    @JoinColumn(name = "personnel_id")
    private Personnel personnel;
    
    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;
    
    @Column(name = "years_of_experience")
    private Double yearsOfExperience;
    
    private Integer proficiency; // 熟練度 1-10
    
    @Column(length = 500)
    private String description;
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

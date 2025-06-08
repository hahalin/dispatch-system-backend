package com.example.dispatch.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personnel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personnel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "english_name", length = 100)
    private String englishName;
    
    @Column(name = "age")
    private Integer age;
    
    @Column(name = "birth_year")
    private Integer birthYear;
    
    @Column(name = "gender", length = 10)
    private String gender;
    
    @Column(name = "status", length = 20)
    private String status = "可派遣";
    
    @Column(name = "phone", length = 20)
    private String phone;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "current_salary")
    private Integer currentSalary;
    
    @Column(name = "expected_salary", length = 50)
    private String expectedSalary;
    
    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction;
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    @Column(name = "current_client", length = 100)
    private String currentClient;
    
    @Column(name = "current_position", length = 100)
    private String currentPosition;
    
    @Column(name = "join_date")
    private LocalDate joinDate;
    
    @Column(name = "dispatch_count")
    private Integer dispatchCount = 0;
    
    @Column(name = "success_rate", precision = 5, scale = 2)
    private BigDecimal successRate = BigDecimal.ZERO;
    
    @Column(name = "last_dispatch_date")
    private LocalDate lastDispatchDate;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // 關聯關係
    @OneToMany(mappedBy = "personnel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Education> educations = new ArrayList<>();
    
    @OneToMany(mappedBy = "personnel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Skill> skills = new ArrayList<>();
    
    @OneToMany(mappedBy = "personnel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Language> languages = new ArrayList<>();
    
    @OneToMany(mappedBy = "personnel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IndustryExperience> industryExperiences = new ArrayList<>();
    
    @OneToMany(mappedBy = "personnel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjectExperience> projectExperiences = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

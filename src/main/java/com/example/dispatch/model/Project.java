package com.example.dispatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer; // 客戶
    
    @Column(nullable = false)
    private String name; // 名稱
    
    @Column(name = "start_date")
    private LocalDate startDate; // 期間開始
    
    @Column(name = "end_date")
    private LocalDate endDate; // 期間結束
    
    @Column(columnDefinition = "TEXT")
    private String content; // 內容
    
    @Column(name = "position_title")
    private String positionTitle; // 職稱
    
    @ManyToOne
    @JoinColumn(name = "created_by")
    private AppUser createdBy;
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProjectSkill> skills = new HashSet<>(); // 技能List
    
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<PersonnelProject> personnel = new HashSet<>();
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

package com.example.dispatch.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelListDTO {
    
    private Integer id;
    private String name;
    private String englishName;
    private Integer age;
    private String status;
    private String statusColor;
    private String currentClient;
    private String currentPosition;
    private String expectedSalary;
    private Double rating;
    private String experience;
    
    // 核心技能（用於列表顯示）
    private List<String> coreSkills;
    
    // 產業經驗（簡化版）
    private List<String> industries;
    
    // 主要技術能力分類
    private TechnicalSkillsSummary technicalSkills;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class TechnicalSkillsSummary {
    private List<String> programming;
    private List<String> frontend;
    private List<String> backend;
    private List<String> database;
} 
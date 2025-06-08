package com.example.dispatch.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelDetailDTO {
    
    private Integer id;
    private String name;
    private String englishName;
    private Integer age;
    private Integer birthYear;
    private String gender;
    private String status;
    private String phone;
    private String email;
    private Integer currentSalary;
    private String expectedSalary;
    private String introduction;
    private String notes;
    private String currentClient;
    private String currentPosition;
    private LocalDate joinDate;
    private Integer dispatchCount;
    private BigDecimal successRate;
    private LocalDate lastDispatchDate;
    
    // 學歷資訊
    private List<EducationDTO> educations;
    
    // 技術技能 - 按類別分組
    private Map<String, List<SkillDetailDTO>> technicalSkills;
    
    // 語言能力
    private List<LanguageDTO> languages;
    
    // 產業經驗
    private List<IndustryExperienceDTO> industryExperience;
    
    // 專案經歷
    private List<ProjectExperienceDTO> projectExperience;
    
    // 能力評估屬性（用於雷達圖）
    private List<AttributeScoreDTO> attributes;
    
    // 綜合評分
    private Double rating;
    
    // 狀態顏色
    private String statusColor;
    
    // 經驗年資（計算得出）
    private String experience;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class EducationDTO {
    private Integer id;
    private String degree;
    private String school;
    private String department;
    private Integer graduationYear;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class SkillDetailDTO {
    private Integer id;
    private String name;
    private Integer years;
    private Integer proficiency;
    private String description;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class LanguageDTO {
    private Integer id;
    private String name;
    private String level;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class IndustryExperienceDTO {
    private Integer id;
    private String industryName;
    private Integer years;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ProjectExperienceDTO {
    private Integer id;
    private String projectName;
    private String client;
    private String company; // 等同於 client，用於前端顯示
    private LocalDate startDate;
    private LocalDate endDate;
    private String period; // 格式化的期間字串
    private String role;
    private String description;
    private Integer teamSize;
    private Integer evaluation;
    private List<String> responsibilities;
    private List<String> achievements; // 可選的成就列表
    private Map<String, List<String>> technologies; // 按類別分組的技術
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class AttributeScoreDTO {
    private String attribute;
    private Integer score;
} 
package com.example.dispatch.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelDTO {
    
    private Integer id;
    
    @NotBlank(message = "姓名不能為空")
    private String name;
    
    private String englishName;
    private Integer age;
    private Integer birthYear;
    private String gender;
    private String status = "可派遣";
    private String phone;
    
    @Email(message = "請輸入有效的電子郵件格式")
    private String email;
    
    private Integer currentSalary;
    private String expectedSalary;
    private String introduction;
    private String notes;
    private String currentClient;
    private String currentPosition;
    private LocalDate joinDate;
    private Integer dispatchCount = 0;
    private BigDecimal successRate = BigDecimal.ZERO;
    private LocalDate lastDispatchDate;
    
    // 關聯資料
    @Valid
    private List<PersonnelEducationDTO> educations;
    
    @Valid
    private List<PersonnelSkillDTO> skills;
    
    @Valid
    private List<PersonnelLanguageDTO> languages;
    
    @Valid
    private List<PersonnelIndustryDTO> industries;
    
    @Valid
    private List<PersonnelProjectDTO> projects;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class PersonnelEducationDTO {
    private Integer id;
    private String degree;
    private String school;
    private String department;
    private Integer graduationYear;
}

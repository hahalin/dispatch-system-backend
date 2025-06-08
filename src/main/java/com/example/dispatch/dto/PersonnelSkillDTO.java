package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelSkillDTO {
    private Integer id;  // 改為 Integer
    private Integer personnelId;  // 改為 Integer
    private Integer skillId;  // 改為 Integer
    private String skillName;
    private String categoryName;
    private Double yearsOfExperience;
    private Integer proficiency; // 熟練度 1-10
    private String description;
}

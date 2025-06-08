package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSkillDTO {
    private Integer id;  // 改為 Integer
    private Integer projectId;  // 改為 Integer
    private Integer skillId;  // 改為 Integer
    private String skillName;
    private String categoryName;
    private Integer importance;
}

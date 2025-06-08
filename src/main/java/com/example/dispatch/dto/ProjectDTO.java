package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Integer id;  // 改為 Integer 以匹配 Entity
    private Integer customerId;  // 改為 Integer 以匹配 Customer Entity
    private String customerName;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private Integer requiredHeadcount;
    private String remarks;
    private List<ProjectSkillDTO> skills;
}

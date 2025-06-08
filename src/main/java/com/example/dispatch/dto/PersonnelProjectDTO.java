package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelProjectDTO {
    private Integer id;  // 改為 Integer
    private Integer personnelId;  // 改為 Integer
    private Integer projectId;  // 改為 Integer
    private String projectName;
    private String customerName;
    private String title;
    private String responsibilities;
    private LocalDate startDate;
    private LocalDate endDate;
}

package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonnelIndustryDTO {
    private Long id;  // BIGSERIAL，保持 Long
    private Integer personnelId;  // 外鍵，改為 Integer
    private String industry;
    private Integer years;
    private String description;
}

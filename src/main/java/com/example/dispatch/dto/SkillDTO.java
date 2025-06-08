package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {
    private Integer id;  // 改為 Integer
    private Integer categoryId;  // 改為 Integer
    private String categoryName;
    private String name;
}

package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerContactDTO {
    private Integer id;  // 改為 Integer
    private Integer customerId;  // 改為 Integer
    private String name;
    private String gender;
    private String department;
    private String title;
    private String phoneExt;
    private String mobile;
}

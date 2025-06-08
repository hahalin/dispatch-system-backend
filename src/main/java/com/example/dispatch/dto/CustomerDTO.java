package com.example.dispatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Integer id;  // 改為 Integer 以匹配 Entity
    private String name;
    private String industry;
    private String address;
}

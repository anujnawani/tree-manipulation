package com.hingehealth.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeDto {
    private Long id;
    private String label;
    private Long parentId;
}
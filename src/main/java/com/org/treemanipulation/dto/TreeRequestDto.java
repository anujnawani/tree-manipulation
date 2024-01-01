package com.hingehealth.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeRequestDto {

    Long parentId;
    @NotBlank(message = "field label in the request is mandatory")
    String label;
}
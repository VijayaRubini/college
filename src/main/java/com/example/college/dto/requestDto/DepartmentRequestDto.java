package com.example.college.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentRequestDto {
    private Long id;
    @NotBlank(message = "Department name is mandatory")
    private String departmentName;
    private Integer departmentStatus;
}

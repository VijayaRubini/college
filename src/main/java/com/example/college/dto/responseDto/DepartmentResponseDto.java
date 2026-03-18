package com.example.college.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentResponseDto {
    private  Long id;
    private  String departmentName;
    private Integer departmentStatus;
    }

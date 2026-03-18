package com.example.college.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffRequestDto {
    private Long id;
    @NotBlank(message = "Staff name is required")
    private String staffName;
    @NotBlank(message ="Staff role is required")
    private String role;
    @NotNull(message = "Salary is required")
    private Double salary;
    private Integer staffStatus;

}

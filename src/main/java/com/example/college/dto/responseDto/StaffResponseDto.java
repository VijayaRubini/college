package com.example.college.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StaffResponseDto {
    private Long id;
    private String staffName;
    private String role;
    private Double salary;
    private Integer staffStatus;

}

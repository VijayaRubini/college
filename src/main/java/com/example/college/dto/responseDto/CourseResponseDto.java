package com.example.college.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseResponseDto {
    private Long id;
    private String courseName;
    private String courseDuration;
    private Integer courseStatus;

}

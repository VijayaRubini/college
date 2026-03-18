package com.example.college.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseRequestDto {
    private Long id;
    @NotBlank(message = "Course name is required")
    private String courseName;
    @NotBlank(message="Course duration is required")
    @Pattern(regexp = "^(\\d+)\\s(month|months|year|years) $",
    message="Course duration must be like 2 months,1 year")
    private String courseDuration;
    private Integer courseStatus;

}

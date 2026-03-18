package com.example.college.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectRequestDto {
    private Long id;
    @NotBlank(message = "Subject name is required")
    private String subjectName;
    @NotBlank(message = "Subject credits is required")
    @Pattern(regexp = "^(\\d)\s(point|points|Point|Points)$")
    private String subjectCredits;
    private Integer subjectStatus;

}

package com.example.college.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubjectResponseDto {
    private Long id;
    private String subjectName;
    private String subjectCredits;
    private Integer subjectStatus;

}

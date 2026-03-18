package com.example.college.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudentResponseDto {
    private Long id;
    private String name;
    private Integer age;
    private String address;
    private String email;
    private Integer status;
}

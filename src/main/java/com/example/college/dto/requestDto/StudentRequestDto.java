package com.example.college.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentRequestDto {
    private Long id;
    @NotBlank(message = "Student name is required")
    private String name;
    @NotNull(message = "Student age is required")
    private Integer age;
    @NotBlank(message = "Address is required")
    private String address;
    @Email
    private String email;
    private Integer status;

}
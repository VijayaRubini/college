package com.example.college.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatusDto {
    private int code;
    private String header;
    private String description;
    private String message;

}

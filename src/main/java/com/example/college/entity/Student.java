package com.example.college.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String address;
    private String email;
    private Integer status;

// getters and setters
}

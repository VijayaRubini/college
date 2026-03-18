package com.example.college.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name="crs_name")
    private String courseName;
    @Column(name="crs_duration")
    private String courseDuration;
    @Column(name="crs_status")
    private Integer courseStatus;


}

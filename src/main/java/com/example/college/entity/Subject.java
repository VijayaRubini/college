package com.example.college.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="subject")
public class Subject {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    private String subjectName;
    private String subjectCredits;
    private Integer subjectStatus;
}

package com.example.college.repository;

import com.example.college.entity.Course;
import com.example.college.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByStatus(Integer status);

}

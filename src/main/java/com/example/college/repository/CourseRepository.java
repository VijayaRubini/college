package com.example.college.repository;

import com.example.college.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByCourseStatus(Integer courseStatus);
}

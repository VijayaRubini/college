package com.example.college.repository;

import com.example.college.entity.Course;
import com.example.college.entity.Department;
import com.example.college.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    List<Department> findByDepartmentStatus(Integer departmentStatus);

}

package com.example.college.repository;

import com.example.college.entity.Course;
import com.example.college.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    List<Staff> findByStaffStatus(Integer staffStatus);

}

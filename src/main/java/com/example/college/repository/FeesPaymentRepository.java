package com.example.college.repository;

import com.example.college.entity.Course;
import com.example.college.entity.FeesPayment;
import com.example.college.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeesPaymentRepository extends JpaRepository<FeesPayment,Long> {
    List<FeesPayment> findByPaymentStatus(Integer paymentStatus);

}




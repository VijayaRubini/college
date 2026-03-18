package com.example.college.repository;

import com.example.college.entity.Course;
import com.example.college.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    List<Subject> findBySubjectStatus(Integer subjectStatus);

}

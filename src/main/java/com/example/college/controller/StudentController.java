package com.example.college.controller;

import com.example.college.dto.requestDto.StudentRequestDto;
import com.example.college.dto.responseDto.StudentResponseDto;
import com.example.college.entity.Student;
import com.example.college.service.StudentService;
import com.example.college.util.ApiResponse;
import com.example.college.util.ApiStatus;
import com.example.college.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveStudentData(@Valid @RequestBody StudentRequestDto studentRequestDto){
          return studentService.saveOrUpdateStudent(studentRequestDto);

    }
    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getActiveStudentData(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getStudentById(@PathVariable Long id){
          return studentService.getStudentById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteStudentData(@PathVariable Long id){
         return studentService.softDeleteStudent(id);
    }

}




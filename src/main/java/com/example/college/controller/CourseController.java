package com.example.college.controller;

import com.example.college.dto.requestDto.CourseRequestDto;
import com.example.college.service.CourseService;
import com.example.college.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveCourseData(@Valid @RequestBody CourseRequestDto courseRequestDto){
         return  courseService.saveOrUpdateCourse(courseRequestDto);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getActiveCourseData(){
        return courseService.getAllCourse();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteCourseData(@PathVariable Long id){
        return courseService.softDeleteCourse(id);
    }

}

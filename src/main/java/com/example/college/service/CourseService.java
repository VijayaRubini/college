package com.example.college.service;

import com.example.college.dto.requestDto.CourseRequestDto;
import com.example.college.dto.responseDto.CourseResponseDto;
import com.example.college.entity.Course;
import com.example.college.exception.InvalidOperationException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.repository.CourseRepository;
import com.example.college.util.ApiResponse;
import com.example.college.util.ApiStatus;
import com.example.college.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public ResponseEntity<ApiResponse<Object>> saveOrUpdateCourse(CourseRequestDto courseRequestDto) {
        Course course;
        //update course
        if (courseRequestDto.getId() != null) {
            course = courseRepository.findById(courseRequestDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Invalid course id "));
        } else {
            course = new Course();
        }
        if (courseRequestDto.getCourseStatus() != null) {
            if (courseRequestDto.getCourseStatus() == -1) {
                throw new InvalidOperationException("Cannot update a deleted course");
            }
        }

        course.setCourseName(courseRequestDto.getCourseName());
        course.setCourseDuration(courseRequestDto.getCourseDuration());

        Integer courseStatus = courseRequestDto.getCourseStatus();
        course.setCourseStatus(courseStatus == null ? 1 : courseStatus);

        courseRepository.save(course);

        boolean isCreate = courseRequestDto.getId() == null;

        return ResponseUtil.build(
                isCreate ? ApiStatus.CREATED : ApiStatus.UPDATED, isCreate ? "Course details added" : "Course details updated", null);

    }

    // get all course(active)
    public ResponseEntity<ApiResponse<Object>> getAllCourse() {
        List<Course> courseList = courseRepository.findByCourseStatus(1);
        List<CourseResponseDto> courseResponseDto = new ArrayList<>();
        if (!courseList.isEmpty()) {
            for (Course course : courseList) {

                courseResponseDto.add(new CourseResponseDto(course.getId(), course.getCourseName(), course.getCourseDuration(), course.getCourseStatus()));

            }
        }
        return ResponseUtil.build(ApiStatus.SUCCESS, "Active courses fetched successfully", courseResponseDto);
    }

    //get course by id
    public ResponseEntity<ApiResponse<Object>> getCourseById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid course id " + id));
        return ResponseUtil.build(ApiStatus.SUCCESS, "Course details are fetched", course);

    }

    //soft delete
    public ResponseEntity<ApiResponse<Object>> softDeleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid course id " + id));
        if (course.getCourseStatus() == -1) {
            throw new InvalidOperationException("Course already deleted");
        }
        course.setCourseStatus(-1);
        courseRepository.save(course);
        return ResponseUtil.build(ApiStatus.DELETED, "Course details deleted successfully", null);


    }

}

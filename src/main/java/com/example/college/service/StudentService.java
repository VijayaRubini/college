package com.example.college.service;

import com.example.college.dto.requestDto.StudentRequestDto;
import com.example.college.dto.responseDto.StudentResponseDto;
import com.example.college.entity.Student;
import com.example.college.exception.InvalidOperationException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.repository.StudentRepository;
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
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<ApiResponse<Object>> saveOrUpdateStudent(StudentRequestDto studentRequestDto){
        Student student;
        //update student
        if(studentRequestDto.getId()!=null){
            student=studentRepository.findById(studentRequestDto.getId()).orElseThrow(()->new ResourceNotFoundException("Invalid student id " ));
        }

        else{
            student=new Student();
        }
        if(studentRequestDto.getStatus() != null){
            if(studentRequestDto.getStatus()==-1){
                throw new InvalidOperationException("Cannot update a deleted student");
            }
        }

        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setAddress(studentRequestDto.getAddress());
        student.setEmail(studentRequestDto.getEmail());

        Integer studentStatus=studentRequestDto.getStatus();
        student.setStatus(studentStatus==null?1:studentStatus);
        studentRepository.save(student);
        boolean isCreate=studentRequestDto.getId()==null;

        return ResponseUtil.build(
                isCreate? ApiStatus.CREATED:ApiStatus.UPDATED,isCreate?"Student details added":"Student details updated",null);

    }

    // get all students(active)
    public ResponseEntity<ApiResponse<Object>> getAllStudents(){
        List<Student> studentList=studentRepository.findByStatus(1);
        List<StudentResponseDto> studentResponseDto =new ArrayList<>();
        if(!studentList.isEmpty()){
            for(Student student: studentList) {

                studentResponseDto.add(new StudentResponseDto(student.getId(), student.getName(), student.getAge(), student.getAddress(),student.getEmail(),student.getStatus()));

            }
        }
        return ResponseUtil.build(ApiStatus.SUCCESS,"Active Students fetched successfully",studentResponseDto);
    }

    //get student by id
    public ResponseEntity<ApiResponse<Object>> getStudentById(Long id){
        Student student= studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid student id " + id));
        return ResponseUtil.build(ApiStatus.SUCCESS,"Student details are fetched",student);

    }

    //soft delete
    public ResponseEntity<ApiResponse<Object>> softDeleteStudent(Long id){
        Student student=studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Invalid course id " +id));
        if(student.getStatus()==-1){
            throw new InvalidOperationException("Student already deleted");
        }
        student.setStatus(-1);
        studentRepository.save(student);
        return ResponseUtil.build(ApiStatus.DELETED,"Student details deleted successfully",null);
    }

}

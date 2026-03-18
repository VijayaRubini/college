package com.example.college.controller;

import com.example.college.dto.requestDto.SubjectRequestDto;
import com.example.college.service.SubjectService;
import com.example.college.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/subject")
public class SubjectController {
        @Autowired
        private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveSubjectData(@Valid @RequestBody SubjectRequestDto subjectRequestDto){
          return subjectService.saveOrUpdateSubject(subjectRequestDto);

    }
    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getActiveSubjectData(){
        return subjectService.getAllSubject();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getSubjectById(@PathVariable Long id){
          return subjectService.getSubjectById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteSubjectData(@PathVariable Long id){
         return subjectService.softDeleteSubject(id);
    }

}

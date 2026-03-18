package com.example.college.controller;

import com.example.college.dto.requestDto.DepartmentRequestDto;
import com.example.college.service.DepartmentService;
import com.example.college.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private  DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveDepartmentData(@Valid @RequestBody DepartmentRequestDto departmentRequestDto){
        return departmentService.saveOrUpdateDepartment(departmentRequestDto);

    }
    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getActiveDepartmentData(){
        return departmentService.getAllDepartment();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getDepartmentById(@PathVariable Long id){
        return departmentService.getDepartmentById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteDepartmentData(@PathVariable Long id){
        return departmentService.softDeleteDepartment(id);
    }

}

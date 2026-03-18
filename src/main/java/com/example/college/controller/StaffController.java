package com.example.college.controller;

import com.example.college.dto.requestDto.StaffRequestDto;
import com.example.college.service.StaffService;
import com.example.college.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private  StaffService staffService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveStaffData(@Valid @RequestBody StaffRequestDto staffRequestDto){
        return staffService.saveOrUpdateStaff(staffRequestDto);

    }
    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getActiveStaffData(){
        return staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getStaffById(@PathVariable Long id){
          return staffService.getStaffById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteStaffData(@PathVariable Long id){
         return staffService.softDeleteStaff(id);
    }

}

package com.example.college.controller;

import com.example.college.dto.requestDto.FeesPaymentRequestDto;
import com.example.college.service.FeesPaymentService;
import com.example.college.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/feesPayment")
public class FeesPaymentController {
    @Autowired
    private  FeesPaymentService feesPaymentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> saveFeesPaymentData(@Valid @RequestBody FeesPaymentRequestDto feesPaymentRequestDto){
          return feesPaymentService.saveOrUpdateFeesPayment(feesPaymentRequestDto);
    }
    @GetMapping
    public ResponseEntity<ApiResponse<Object>> getActiveFeesPaymentData(){
        return feesPaymentService.getAllFeesPayment();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> getFeesPaymentById(@PathVariable Long id){
        return feesPaymentService.getFeesPaymentById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteCourseData(@PathVariable Long id){
        return feesPaymentService.softDeleteFeesPayment(id);
    }

}

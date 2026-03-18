package com.example.college.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FeesPaymentResponseDto {
    private  Long id;
    private  Long studentId;
    private  Double amount;
    private String paymentMode;
    private LocalDate paymentDate;
    private  Integer paymentStatus;

}

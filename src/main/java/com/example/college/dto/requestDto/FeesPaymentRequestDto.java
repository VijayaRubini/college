package com.example.college.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeesPaymentRequestDto {
    private Long id;
    @NotNull(message = "Student id is required")
    private Long studentId;
    private Double amount;
    @NotNull(message = "Payment mode is required")
    @Pattern(regexp = "^(CASH|CARD|UPI|NET_BANKING)$",
            message = "Payment mode must be CASH, CARD, UPI, or NET_BANKING")
    private String paymentMode;
    @NotNull(message="Date is required")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate paymentDate;
    private Integer paymentStatus;
}

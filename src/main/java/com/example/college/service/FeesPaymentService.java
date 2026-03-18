package com.example.college.service;

import com.example.college.dto.requestDto.FeesPaymentRequestDto;
import com.example.college.dto.responseDto.FeesPaymentResponseDto;
import com.example.college.entity.FeesPayment;
import com.example.college.exception.InvalidOperationException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.repository.FeesPaymentRepository;
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
public class FeesPaymentService {
    @Autowired
    private FeesPaymentRepository feesPaymentRepository;

    public ResponseEntity<ApiResponse<Object>> saveOrUpdateFeesPayment(FeesPaymentRequestDto feesPaymentRequestDto){
        FeesPayment feesPayment;
        //update fees payment
        if(feesPaymentRequestDto.getId()!=null){
            feesPayment=feesPaymentRepository.findById(feesPaymentRequestDto.getId()).orElseThrow(()->new ResourceNotFoundException("Invalid fees payment id "));
        }

        else{
            feesPayment=new FeesPayment();
        }
        if(feesPaymentRequestDto.getPaymentStatus() != null){
            if(feesPaymentRequestDto.getPaymentStatus()==-1){
                throw new InvalidOperationException("Cannot update a deleted fees payment");
            }
        }

        feesPayment.setStudentId(feesPaymentRequestDto.getStudentId());
        feesPayment.setAmount(feesPaymentRequestDto.getAmount());
        feesPayment.setPaymentMode(feesPaymentRequestDto.getPaymentMode());
        feesPayment.setPaymentDate(feesPaymentRequestDto.getPaymentDate());

        Integer paymentStatus=feesPaymentRequestDto.getPaymentStatus();
        feesPayment.setPaymentStatus(paymentStatus==null?1:paymentStatus);

        feesPaymentRepository.save(feesPayment);

        boolean isCreate=feesPaymentRequestDto.getId()==null;
        return ResponseUtil.build(
                isCreate? ApiStatus.CREATED:ApiStatus.UPDATED,isCreate?"Fees Payment details added":"Fees payment details updated",null);

    }

    // get all fees payment(active)
    public ResponseEntity<ApiResponse<Object>> getAllFeesPayment(){
        List<FeesPayment> feesPaymentList=feesPaymentRepository.findByPaymentStatus(1);
        List<FeesPaymentResponseDto> feesPaymentResponseDto =new ArrayList<>();
        if(!feesPaymentList.isEmpty()){
            for(FeesPayment feesPayment: feesPaymentList) {

                feesPaymentResponseDto.add(new FeesPaymentResponseDto(feesPayment.getId(), feesPayment.getStudentId(), feesPayment.getAmount(), feesPayment.getPaymentMode(), feesPayment.getPaymentDate(), feesPayment.getPaymentStatus()));

            }
        }
        return ResponseUtil.build(ApiStatus.SUCCESS,"Active fees payment fetched successfully",feesPaymentResponseDto);
    }

    //get fees payment by id
    public ResponseEntity<ApiResponse<Object>> getFeesPaymentById(Long id){
        FeesPayment feesPayment= feesPaymentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid fees payment id "+ id));
        return ResponseUtil.build(ApiStatus.SUCCESS,"Fees payment details are fetched",feesPayment);

    }

    //soft delete
    public ResponseEntity<ApiResponse<Object>> softDeleteFeesPayment(Long id){
        FeesPayment feesPayment=feesPaymentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Invalid fees payment id "+id));
        if(feesPayment.getPaymentStatus()==-1){
            throw new InvalidOperationException("Fees payment already deleted");
        }
        feesPayment.setPaymentStatus(-1);
        feesPaymentRepository.save(feesPayment);
        return ResponseUtil.build(ApiStatus.DELETED,"Fees payment details deleted successfully",null);
    }

}

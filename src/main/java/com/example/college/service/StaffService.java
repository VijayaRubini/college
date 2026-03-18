package com.example.college.service;
import com.example.college.dto.requestDto.StaffRequestDto;
import com.example.college.dto.responseDto.StaffResponseDto;
import com.example.college.entity.Staff;
import com.example.college.exception.InvalidOperationException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.repository.StaffRepository;
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
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    public ResponseEntity<ApiResponse<Object>> saveOrUpdateStaff(StaffRequestDto staffRequestDto){
        Staff staff;
        //update staff
        if(staffRequestDto.getId()!=null){
            staff=staffRepository.findById(staffRequestDto.getId()).orElseThrow(()->new ResourceNotFoundException("Invalid staff id " ));
        }

        else{
            staff=new Staff();
        }
        if(staffRequestDto.getStaffStatus() != null){
            if(staffRequestDto.getStaffStatus()==-1){
                throw new InvalidOperationException("Cannot update a deleted staff");
            }
        }

        staff.setStaffName(staffRequestDto.getStaffName());
        staff.setRole(staffRequestDto.getRole());
        staff.setSalary(staffRequestDto.getSalary());
        staff.setStaffName(staffRequestDto.getStaffName());

        Integer staffStatus=staffRequestDto.getStaffStatus();
        staff.setStaffStatus(staffStatus==null?1:staffStatus);
        staffRepository.save(staff);
        boolean isCreate=staffRequestDto.getId()==null;

        return ResponseUtil.build(
                isCreate? ApiStatus.CREATED:ApiStatus.UPDATED,isCreate?"Staff details added":"Staff details updated",null);

    }

    // get all staff(active)
    public ResponseEntity<ApiResponse<Object>> getAllStaff(){
        List<Staff> staffList=staffRepository.findByStaffStatus(1);
        List<StaffResponseDto> staffResponseDto =new ArrayList<>();
        if(!staffList.isEmpty()){
            for(Staff staff: staffList) {

                staffResponseDto.add(new StaffResponseDto(staff.getId(), staff.getStaffName(), staff.getRole(), staff.getSalary(),staff.getStaffStatus()));

            }
        }
        return ResponseUtil.build(ApiStatus.SUCCESS,"Active Staffs fetched successfully",staffResponseDto);
    }

    //get staff by id
    public ResponseEntity<ApiResponse<Object>> getStaffById(Long id){
        Staff staff= staffRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid staff id "+id));
        return ResponseUtil.build(ApiStatus.SUCCESS,"Staff details are fetched",staff);

    }

    //soft delete
    public ResponseEntity<ApiResponse<Object>> softDeleteStaff(Long id){
        Staff staff=staffRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Invalid staff id "+id));
        if(staff.getStaffStatus()==-1){
            throw new InvalidOperationException("Staff already deleted");
        }
        staff.setStaffStatus(-1);
        staffRepository.save(staff);
        return ResponseUtil.build(ApiStatus.DELETED,"Staff details deleted successfully",null);
    }

}

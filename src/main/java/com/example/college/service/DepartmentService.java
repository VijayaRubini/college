package com.example.college.service;
import com.example.college.dto.requestDto.DepartmentRequestDto;
import com.example.college.dto.responseDto.DepartmentResponseDto;
import com.example.college.entity.Department;
import com.example.college.exception.InvalidOperationException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.repository.DepartmentRepository;
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
public class DepartmentService {
    @Autowired
    private  DepartmentRepository departmentRepository;

    public ResponseEntity<ApiResponse<Object>> saveOrUpdateDepartment(DepartmentRequestDto departmentRequestDto){
        Department department;
        //update department
        if(departmentRequestDto.getId()!=null){
            department=departmentRepository.findById(departmentRequestDto.getId()).orElseThrow(()->new ResourceNotFoundException("Invalid department id " ));
        }
        //create department
        else{
            department=new Department();
        }
        if(departmentRequestDto.getDepartmentStatus() != null){
            if(departmentRequestDto.getDepartmentStatus()==-1){
                throw new InvalidOperationException("Cannot update a deleted department");
            }
        }

        department.setDepartmentName(departmentRequestDto.getDepartmentName());

        Integer departmentStatus=departmentRequestDto.getDepartmentStatus();
        department.setDepartmentStatus(departmentStatus==null?1:departmentStatus);

        departmentRepository.save(department);

        boolean isCreate=departmentRequestDto.getId()==null;
        return ResponseUtil.build(
                isCreate?ApiStatus.CREATED:ApiStatus.UPDATED,isCreate?"Department details added":"Department details updated",null);

    }

    //get all department(active)
    public ResponseEntity<ApiResponse<Object>> getAllDepartment(){
        List<Department> departmentList=departmentRepository.findAll();
        List<DepartmentResponseDto> departmentResponseDto =new ArrayList<>();
        if(!departmentList.isEmpty()){
        for(Department department: departmentList) {
                departmentResponseDto.add(new DepartmentResponseDto(department.getId(), department.getDepartmentName(), department.getDepartmentStatus()));
            }
        }
        return ResponseUtil.build(ApiStatus.SUCCESS,"Active departments fetched successfully",departmentResponseDto);
    }

    //get department by id
    public ResponseEntity<ApiResponse<Object>> getDepartmentById(Long id){
       Department department= departmentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid department id"));
       return ResponseUtil.build(ApiStatus.SUCCESS,"Department details are fetched",department);

    }

    //soft delete
    public ResponseEntity<ApiResponse<Object>> softDeleteDepartment(Long id){
        Department department=departmentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid department id "+id));
        if(department.getDepartmentStatus()==-1){
            throw new InvalidOperationException("Department already deleted");
        }
        department.setDepartmentStatus(-1);
        departmentRepository.save(department);
        return ResponseUtil.build(ApiStatus.DELETED,"Department details deleted successfully",null);

    }

}










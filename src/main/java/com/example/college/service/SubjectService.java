package com.example.college.service;

import com.example.college.dto.requestDto.SubjectRequestDto;
import com.example.college.dto.responseDto.SubjectResponseDto;
import com.example.college.entity.Subject;
import com.example.college.exception.InvalidOperationException;
import com.example.college.exception.ResourceNotFoundException;
import com.example.college.repository.SubjectRepository;
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
public class SubjectService {
    @Autowired
    private  SubjectRepository subjectRepository;

    public ResponseEntity<ApiResponse<Object>> saveOrUpdateSubject(SubjectRequestDto subjectRequestDto){
        Subject subject;
        //update subject
        if(subjectRequestDto.getId()!=null){
            subject=subjectRepository.findById(subjectRequestDto.getId()).orElseThrow(()->new ResourceNotFoundException("Invalid subject id "));
        }

        else{
            subject=new Subject();
        }

        if(subjectRequestDto.getSubjectStatus() != null){
            if(subjectRequestDto.getSubjectStatus()==-1){
                throw new InvalidOperationException("Cannot update a deleted subject");
            }
        }

        subject.setSubjectName(subjectRequestDto.getSubjectName());
        subject.setSubjectCredits(subjectRequestDto.getSubjectCredits());

        Integer subjectStatus=subjectRequestDto.getSubjectStatus();
        subject.setSubjectStatus(subjectStatus==null?1:subjectStatus);
        subjectRepository.save(subject);
        boolean isCreate=subjectRequestDto.getId()==null;
        return ResponseUtil.build(
                isCreate? ApiStatus.CREATED:ApiStatus.UPDATED,isCreate?"Subject details are added":"Subject details updated",null
        );
    }

    // get all subject(active)
    public ResponseEntity<ApiResponse<Object>> getAllSubject(){
        List<Subject> subjectList=subjectRepository.findBySubjectStatus(1);
        List<SubjectResponseDto> subjectResponseDto =new ArrayList<>();
        if(!subjectList.isEmpty()){
            for(Subject subject: subjectList) {

                subjectResponseDto.add(new SubjectResponseDto(subject.getId(), subject.getSubjectName(), subject.getSubjectCredits(), subject.getSubjectStatus()));

            }
        }
        return ResponseUtil.build(ApiStatus.SUCCESS,"Active subject fetched successfully",subjectResponseDto);
    }

    //get subject by id
    public ResponseEntity<ApiResponse<Object>> getSubjectById(Long id){
        Subject subject= subjectRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid subject id "+ id));
        return ResponseUtil.build(ApiStatus.SUCCESS,"Subject details are fetched",subject);

    }

    //soft delete
    public ResponseEntity<ApiResponse<Object>> softDeleteSubject(Long id){
        Subject subject=subjectRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Invalid subject id "+id));
        if(subject.getSubjectStatus()==-1){
            throw new InvalidOperationException("Subject already deleted");
        }
        subject.setSubjectStatus(-1);
        subjectRepository.save(subject);
        return ResponseUtil.build(ApiStatus.DELETED,"Subject details are deleted successfully",null);
    }

}

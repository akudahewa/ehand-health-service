package com.zerotoproduction.bucketlist.controller;

import com.zerotoproduction.bucketlist.domain.Dispensary;
import com.zerotoproduction.bucketlist.domain.DispensaryUser;
import com.zerotoproduction.bucketlist.domain.Doctor;
import com.zerotoproduction.bucketlist.domain.DoctorDispensary;
import com.zerotoproduction.bucketlist.dto.DispensaryUserDTO;
import com.zerotoproduction.bucketlist.dto.DoctorDispensaryDTO;
import com.zerotoproduction.bucketlist.exception.ResourceNotFoundException;
import com.zerotoproduction.bucketlist.repository.IDispensaryRepository;
import com.zerotoproduction.bucketlist.repository.IDispensaryUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
@RequiredArgsConstructor
public class DispensaryUserController {

    private final IDispensaryUserRepository dispensaryUserRepository;
    private final IDispensaryRepository dispensaryRepository;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/dispensary-users")
    public DispensaryUser saveDispensaryUser(@RequestBody DispensaryUserDTO dispensaryUserDTO){
        System.out.println(":::::::::::::::::::::::::::::::::: user");
        System.out.println("POST - save DispensaryUser dispensaryId:{} ,userId :{}"+dispensaryUserDTO.getDispensary()+","+
                dispensaryUserDTO.getUserId()+">>> "+dispensaryUserDTO.getName());
        DispensaryUser dispensaryUser = new DispensaryUser();

        Dispensary dispensary = dispensaryRepository.findById(dispensaryUserDTO.getDispensary())
                .orElseThrow(()->new ResourceNotFoundException("Dispensary not found "));
        dispensaryUser.setUserId(dispensaryUserDTO.getUserId());
        dispensaryUser.setDispensary(dispensary);
        dispensaryUser.setName(dispensaryUserDTO.getName());
        dispensaryUser.setRole(dispensaryUserDTO.getRole());
        dispensaryUser.setStatus(dispensaryUserDTO.getStatus());
        System.out.println("........................... save users ................................"+dispensaryUser.getUserId());
        return dispensaryUserRepository.save(dispensaryUser);
    }

    @GetMapping(value = "/dispensary-users")
    public ResponseEntity<DispensaryUser> getDispensary(@RequestParam(required = false) String userId
    ){
        System.out.println("........................id................."+userId);
        log.info("GET - DispensaryUser dispensaryId :{}",userId);
        DispensaryUser dispensaryUser = dispensaryUserRepository.findByUserId(userId);
        return ResponseEntity.ok().body(dispensaryUser);
    }
}

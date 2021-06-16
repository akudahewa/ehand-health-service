package com.zerotoproduction.bucketlist.controller;

import com.zerotoproduction.bucketlist.domain.Dispensary;
import com.zerotoproduction.bucketlist.domain.Doctor;
import com.zerotoproduction.bucketlist.exception.ResourceNotFoundException;
import com.zerotoproduction.bucketlist.repository.IDoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorRepository doctorRepository;

    /**
     * save doctor
     * @param doctor
     * @return saved doctor object
     */
    @PostMapping(value = "/doctor",consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> saveCity(@RequestBody Doctor doctor){
        log.info("POST - save city :{}",doctor);

        return new ResponseEntity<>(doctorRepository.save(doctor), HttpStatus.CREATED);
    }

    /**
     * Get all doctor
     * @return all doctors
     */
    @GetMapping(value = "/doctor",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Doctor>> getDoctor(){
        log.info("GET - Get all doctors ");

        return  ResponseEntity.ok(doctorRepository.findAll());
    }

    /**
     * Get doctor object for given doctorId
     * @param id
     * @return doctor object
     */
    @GetMapping(value = "/doctor/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Doctor getDoctor(@PathVariable String id){
        log.info("GET - Get all doctors :{}",id);

        return doctorRepository.findById(Long.parseLong(id))
                .orElseThrow(()->new ResourceNotFoundException("Doctor not found"));
    }

}

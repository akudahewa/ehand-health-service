package lk.ehand.healthservice.controller;

import lk.ehand.healthservice.domain.Dispensary;
import lk.ehand.healthservice.domain.Doctor;
import lk.ehand.healthservice.exception.ResourceNotFoundException;
import lk.ehand.healthservice.repository.IDoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
public class DoctorController {

    @Autowired
    IDoctorRepository doctorRepository;

    @PostMapping(value = "/doctor")
    public ResponseEntity<Doctor> saveCity(@RequestBody Doctor doctor){
        log.info("POST - save city :{}",doctor);
        return new ResponseEntity<>(doctorRepository.save(doctor), HttpStatus.CREATED);

    }

    @GetMapping(value = "/doctor")
    public List<Doctor> getDoctor(){
        log.info("GET - Get all doctors ");
        return doctorRepository.findAll();
    }

    @GetMapping(value = "/doctor/{id}")
    public Doctor getDoctor(@PathVariable String id){
        log.info("GET - Get all doctors :{}",id);
        return doctorRepository.findById(Long.parseLong(id))
                .orElseThrow(()->new ResourceNotFoundException("Doctor not found"));
    }

}

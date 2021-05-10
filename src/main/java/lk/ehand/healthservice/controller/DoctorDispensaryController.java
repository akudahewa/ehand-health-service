package lk.ehand.healthservice.controller;

import lk.ehand.healthservice.domain.City;
import lk.ehand.healthservice.domain.Dispensary;
import lk.ehand.healthservice.domain.Doctor;
import lk.ehand.healthservice.domain.DoctorDispensary;
import lk.ehand.healthservice.dto.DoctorDispensaryDTO;
import lk.ehand.healthservice.exception.ResourceNotFoundException;
import lk.ehand.healthservice.repository.IDispensaryRepository;
import lk.ehand.healthservice.repository.IDoctorDispensaryRepository;
import lk.ehand.healthservice.repository.IDoctorRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
@Data
public class DoctorDispensaryController {

    @Autowired
    IDoctorDispensaryRepository doctorDispensaryRepository;
    @Autowired
    IDoctorRepository doctorRepository;
    @Autowired
    IDispensaryRepository dispensaryRepository;

    @PostMapping(value = "/doctor/{doctorId}/dispensary/{dispensaryId}")
    public DoctorDispensary saveDoctorDispensary(@PathVariable String doctorId,@PathVariable String dispensaryId,
                                     @RequestBody DoctorDispensary doctorDispensary){

//        log.info("POST - save DoctorDispensary :{}",doctorDispensary);
         Dispensary dispensary = dispensaryRepository.findById(Long.parseLong(dispensaryId))
                 .orElseThrow(()->new ResourceNotFoundException("Dispensary not found "));
         Doctor doctor =doctorRepository.findById(Long.parseLong(doctorId))
                .orElseThrow(()->new ResourceNotFoundException("Doctor not found "));
         doctorDispensary.setDoctor(doctor);
         doctorDispensary.setDispensary(dispensary);
         return doctorDispensaryRepository.save(doctorDispensary);

    }
}

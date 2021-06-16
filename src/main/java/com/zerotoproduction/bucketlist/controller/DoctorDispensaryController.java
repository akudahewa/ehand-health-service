package com.zerotoproduction.bucketlist.controller;

import com.zerotoproduction.bucketlist.domain.City;
import com.zerotoproduction.bucketlist.domain.Dispensary;
import com.zerotoproduction.bucketlist.domain.Doctor;
import com.zerotoproduction.bucketlist.domain.DoctorDispensary;
import com.zerotoproduction.bucketlist.dto.DoctorDispensaryDTO;
import com.zerotoproduction.bucketlist.exception.ResourceNotFoundException;
import com.zerotoproduction.bucketlist.repository.IDispensaryRepository;
import com.zerotoproduction.bucketlist.repository.IDoctorDispensaryRepository;
import com.zerotoproduction.bucketlist.repository.IDoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
@RequiredArgsConstructor
public class DoctorDispensaryController {

    private final IDoctorDispensaryRepository doctorDispensaryRepository;
    private final IDoctorRepository doctorRepository;
    private final IDispensaryRepository dispensaryRepository;

    @Autowired
    ModelMapper modelMapper;

    /**
     * save doctor and dispensary combined
     * @param DoctorDispensaryDTO
     * @return saved doctor-dispensary object
     */
    @PostMapping(value = "/doctor-dispensary")
    public DoctorDispensary saveDoctorDispensary(@RequestBody DoctorDispensaryDTO doctorDispensaryDTO){

        log.info("POST - save DoctorDispensary doctorId:{} ,dispensaryId :{}",doctorDispensaryDTO.getDoctor(),
                doctorDispensaryDTO.getDispensary());
        DoctorDispensary doctorDispensary= modelMapper.map(doctorDispensaryDTO,DoctorDispensary.class);
        Dispensary dispensary = dispensaryRepository.findById(Long.parseLong(doctorDispensaryDTO.getDispensary()))
                 .orElseThrow(()->new ResourceNotFoundException("Dispensary not found "));
        Doctor doctor =doctorRepository.findById(Long.parseLong(doctorDispensaryDTO.getDoctor()))
                .orElseThrow(()->new ResourceNotFoundException("Doctor not found "));
        doctorDispensary.setDoctor(doctor);
        doctorDispensary.setDispensary(dispensary);

        return doctorDispensaryRepository.save(doctorDispensary);
    }

    /**
     * Get all doctor-dispensaries by doctor or dispensary or both
     * @param doctorId
     * @param dispensaryId
     * @return list of doctor-dispensary objects
     */
    @GetMapping(value = "/doctor-dispensary")
    public ResponseEntity<List<DoctorDispensary>> getDoctorDispensary(@RequestParam(required = false) String doctorId,
                                                @RequestParam(required = false) String dispensaryId
                                                 ){

       log.info("GET - DoctorDispensary doctorId:{}, dispensaryId :{}",doctorId,dispensaryId);
        List<DoctorDispensary> doctorDispensary = doctorDispensaryRepository.
                findAllByDoctorAndDispensary( doctorId !=null ?Long.parseLong(doctorId):null,
                        dispensaryId !=null ?Long.parseLong(dispensaryId):null);

        return ResponseEntity.ok().body(doctorDispensary);
    }

    @GetMapping(value = "/doctor-dispensary/{cityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorDispensary> getMainSearchData(@PathVariable("cityId") String cityId){
        List<DoctorDispensary> doctorDispensaryList=
                doctorDispensaryRepository.findAllByDispensary(Long.parseLong(cityId));

        return doctorDispensaryList;

    }

}

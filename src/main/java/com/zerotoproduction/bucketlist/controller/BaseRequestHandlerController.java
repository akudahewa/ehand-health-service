package com.zerotoproduction.bucketlist.controller;

import com.zerotoproduction.bucketlist.domain.DoctorDispensary;
import com.zerotoproduction.bucketlist.service.DoctorServiceImpl;
import com.zerotoproduction.bucketlist.service.IDoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
public class BaseRequestHandlerController {

    private IDoctorService doctorService;
    @Autowired
    BaseRequestHandlerController(DoctorServiceImpl doctorService){
        this.doctorService =doctorService;
    }

    @GetMapping(value = "/search/{cityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorDispensary> getMainSearchData(@PathVariable("cityId") String cityId){
        return doctorService.getDoctorByCity(Long.parseLong(cityId));

    }
}

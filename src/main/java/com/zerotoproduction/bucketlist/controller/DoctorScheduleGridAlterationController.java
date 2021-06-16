//package com.zerotoproduction.bucketlist.controller;
//
//import com.zerotoproduction.bucketlist.domain.DoctorScheduleGrid;
//import com.zerotoproduction.bucketlist.domain.DoctorScheduleGridAlteration;
//import com.zerotoproduction.bucketlist.exception.ResourceNotFoundException;
//import com.zerotoproduction.bucketlist.repository.IDoctorScheduleGridAlterationRepository;
//import com.zerotoproduction.bucketlist.repository.IDoctorScheduleGridRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin(origins = "*")
//@RequestMapping(value = "/api")
//@Slf4j
//public class DoctorScheduleGridAlterationController {
//
//    @Autowired
//    IDoctorScheduleGridRepository doctorScheduleGridRepository;
//    @Autowired
//    IDoctorScheduleGridAlterationRepository doctorScheduleGridAlterationRepository;
//
//    @PostMapping(value = "/schedule/{scheduleId}/alteration")
//    public DoctorScheduleGridAlteration saveCity(@RequestBody DoctorScheduleGridAlteration doctorScheduleGridAlteration,
//                                       @PathVariable("scheduleId") String scheduleId
//    ){
//        System.out.println("==================Alteration ========="+scheduleId);
//        return doctorScheduleGridRepository.findById(Long.parseLong(scheduleId)).map(schedule ->{
//            doctorScheduleGridAlteration.setDoctorScheduleGrid(schedule);
//            return doctorScheduleGridAlterationRepository.save(doctorScheduleGridAlteration);
//        }).orElseThrow(()->new ResourceNotFoundException("District Not found"));
//    }
//}

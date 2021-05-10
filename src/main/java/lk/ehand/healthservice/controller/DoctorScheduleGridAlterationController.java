//package lk.ehand.healthservice.controller;
//
//import lk.ehand.healthservice.domain.DoctorScheduleGrid;
//import lk.ehand.healthservice.domain.DoctorScheduleGridAlteration;
//import lk.ehand.healthservice.exception.ResourceNotFoundException;
//import lk.ehand.healthservice.repository.IDoctorScheduleGridAlterationRepository;
//import lk.ehand.healthservice.repository.IDoctorScheduleGridRepository;
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

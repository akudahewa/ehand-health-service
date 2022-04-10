package com.zerotoproduction.bucketlist.controller;

import org.modelmapper.ModelMapper;
import com.zerotoproduction.bucketlist.domain.*;
import com.zerotoproduction.bucketlist.dto.DoctorScheduleGridDTO;
import com.zerotoproduction.bucketlist.exception.ResourceNotFoundException;
import com.zerotoproduction.bucketlist.repository.IDoctorDispensaryRepository;
import com.zerotoproduction.bucketlist.repository.IDoctorScheduleGridAlterationRepository;
import com.zerotoproduction.bucketlist.repository.IDoctorScheduleGridRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
public class DoctorScheduleGridController {

    private final IDoctorDispensaryRepository doctorDispensaryRepository;
    private final IDoctorScheduleGridRepository doctorScheduleGridRepository;
    private final IDoctorScheduleGridAlterationRepository doctorScheduleGridAlterationRepository;

    @Autowired
    private ApplicationContext appContext;

    public DoctorScheduleGridController(IDoctorDispensaryRepository doctorDispensaryRepository,
                                        IDoctorScheduleGridRepository doctorScheduleGridRepository,
                                        IDoctorScheduleGridAlterationRepository doctorScheduleGridAlterationRepository){
        this.doctorDispensaryRepository =doctorDispensaryRepository;
        this.doctorScheduleGridRepository = doctorScheduleGridRepository;
        this.doctorScheduleGridAlterationRepository = doctorScheduleGridAlterationRepository;
    }

    /**
     * save doctor schedules
     * @param doctorScheduleGridDTO
     * @param doctorDispensaryId
     * @return saved doctor schedule
     */
    @PostMapping(value = "/schedule/{doctorDispensaryId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorScheduleGrid> saveCity(@RequestBody DoctorScheduleGridDTO doctorScheduleGridDTO,
                                                       @PathVariable("doctorDispensaryId") String doctorDispensaryId
    ){
        log.info("POST - save city :{}",doctorScheduleGridDTO);
        ModelMapper objectMapper = (ModelMapper) appContext.getBean("modelMapper");
        DoctorScheduleGrid doctorScheduleGrid = objectMapper.map(doctorScheduleGridDTO,DoctorScheduleGrid.class);
        DoctorDispensary doctorDispensary= doctorDispensaryRepository.findById(Long.parseLong(doctorDispensaryId))
                .orElseThrow(()-> new ResourceNotFoundException("No matching doctor and dispensary found"));
        doctorScheduleGrid.setDoctorDispensary(doctorDispensary);

        return new ResponseEntity(doctorScheduleGridRepository.save(doctorScheduleGrid), HttpStatus.CREATED);
    }

    /**
     *  Returns list of doctor schedules according to the doctor and dispensary
     * @param doctorId
     * @param dispensaryId
     * @param displayDays
     * @return list of doctor schedules
     */
    @GetMapping(value = "/schedule/doctor/{doctorId}/dispensary/{dispensaryId}")
    public List<DoctorAppoinmentSession> getNextAvailableDoctorSessions(@PathVariable("doctorId") String doctorId,
                                                                        @PathVariable("dispensaryId") String dispensaryId,
                                                                        @RequestParam(required = true,defaultValue = "1") int displayDays
    ) {
        log.info("GET- get sechedules -doctorId :{}, dispensaryId :{},noOfDays :{}", doctorId, dispensaryId, displayDays);
        List<String> days = new ArrayList<>();
        LocalDate currentdate = LocalDate.now();
        days.add(currentdate.getDayOfWeek().toString());

        Map<String, DoctorAppoinmentSession> doctorSessions = new HashMap<>();
        IntStream.range(1, displayDays).forEach(
                nbr -> {
                    days.add(currentdate.plusDays(nbr).getDayOfWeek().toString());
                }
        );
        Map<String, List<DoctorScheduleGridAlteration>> doctorScheduleAlterationMap = new HashMap<>();
        List<DoctorScheduleGrid> results = doctorScheduleGridRepository
                .findByDoctorAndDispensaryAndDayOfWeek(Long.parseLong(doctorId), Long.parseLong(dispensaryId), days);

        Map<String, List<AppointmentTimeProcessor>> resultMap = new HashMap<>();
        List<DoctorAppoinmentSession> doctorAppoinmentSessionList = new ArrayList<>();

        results.forEach(schedule -> {
            List<AppointmentTimeProcessor> appointmentTimeProcessorList = new ArrayList<>();
            List<DoctorScheduleGridAlteration> absentList = new ArrayList<>();
            List<DoctorScheduleGridAlteration> amendList = new ArrayList<>();

            AppointmentTimeProcessor appointmentTimeProcessor = new AppointmentTimeProcessor();
            String key = String.valueOf(schedule.getId()).concat("-").concat(schedule.getDayOfWeek());

            appointmentTimeProcessor.setDoctorScheduleGridId(schedule.getId());
            appointmentTimeProcessor.setDayOfWeek(schedule.getDayOfWeek());
            appointmentTimeProcessor.setSessionStart(schedule.getSessionStart());
            appointmentTimeProcessor.setSessionEnd(schedule.getSessionEnd());
            appointmentTimeProcessor.setStatus(schedule.getStatus());
            appointmentTimeProcessor.setMaxCount(schedule.getMaxCount());
            System.out.println("=========doctorScheduleGridId "+schedule.getId()+"..DAY... "+schedule.getDayOfWeek());
            if (schedule.getDoctorScheduleGridAlterationList().size() > 0) {
                schedule.getDoctorScheduleGridAlterationList().forEach(d -> {
                    if (d.getStatus().equals("ABSENT")) {
                        absentList.add(d);
                    } else if (d.getStatus().equals("AMAND")) {
                        amendList.add(d);
                    }
                });
                appointmentTimeProcessor.setAbsentList(absentList);
                appointmentTimeProcessor.setAmendList((amendList));
                //appointmentTimeProcessorList.add(appointmentTimeProcessor);
                //System.out.println("getDoctorScheduleGridAlterationList >0  :"+appointmentTimeProcessor.getDoctorScheduleGridId());
//                resultMap.computeIfPresent(key,
//                        (k, v) -> addAlteration1(v, appointmentTimeProcessorList));
                //System.out.println("getDoctorScheduleGridAlterationList >0  - computeIfPresent "+appointmentTimeProcessorList.size());
               // resultMap.putIfAbsent(key, appointmentTimeProcessorList);

               // System.out.println("getDoctorScheduleGridAlterationList >0 - size- putIfAbsent "+appointmentTimeProcessorList.size());


            }
           // else{
                System.out.println("getDoctorScheduleGridAlterationList ==0 "+appointmentTimeProcessor.getDoctorScheduleGridId()+
                        " --- day ---"+appointmentTimeProcessor.getDayOfWeek());
                appointmentTimeProcessorList.add(appointmentTimeProcessor);
                resultMap.putIfAbsent(key, appointmentTimeProcessorList);
                System.out.println("getDoctorScheduleGridAlterationList ==0 size  "+appointmentTimeProcessorList.size());
            //}///

        });
        IntStream.range(0, displayDays).forEach(i -> {
            Set<String> set = resultMap.keySet()
                    .stream()
                    .filter(s -> s.endsWith(String.valueOf(currentdate.plusDays(i).getDayOfWeek())))
                    .collect(Collectors.toSet());

            set.forEach(s->{
                List<AppointmentTimeProcessor> appoinmentSchedule= resultMap.get(s);

                List<DoctorScheduleGridAlteration> doctorAbsent = appoinmentSchedule.get(0).getAbsentList();
                List<DoctorScheduleGridAlteration> doctorAmand= appoinmentSchedule.get(0).getAmendList();
                DoctorScheduleGridAlteration doctorScheduleGridAlteration = new DoctorScheduleGridAlteration();

                if(doctorAbsent!=null && doctorAbsent.size()>0){
                    doctorScheduleGridAlteration =
                            doctorAbsent.stream().filter(absent->absent.getDate().equals(String.valueOf(currentdate.plusDays(i)))).findFirst().get();
                }
                if(doctorAmand !=null && doctorAmand.size()>0 )  {
                    doctorScheduleGridAlteration =
                            doctorAmand.stream().filter(amand->amand.getDate().equals(String.valueOf(currentdate.plusDays(i)))).findFirst().get();
                }
                DoctorAppoinmentSession appoinmentSessionBean=
                        (DoctorAppoinmentSession) appContext.getBean("doctorSession");
                if( doctorAbsent!=null){
                    appoinmentSessionBean.setId(appoinmentSchedule.get(0).getDoctorScheduleGridId());
                    appoinmentSessionBean.setSessionStartTime(appoinmentSchedule.get(0).getSessionStart());
                    appoinmentSessionBean.setSessionEndTime(appoinmentSchedule.get(0).getSessionEnd());
                    appoinmentSessionBean.setDay(appoinmentSchedule.get(0).getDayOfWeek());
                    appoinmentSessionBean.setDate(String.valueOf(currentdate.plusDays(i)));
                    appoinmentSessionBean.setStatus(doctorScheduleGridAlteration.getStatus());
                    appoinmentSessionBean.setMaxCount(appoinmentSchedule.get(0).getMaxCount());

                    doctorAppoinmentSessionList.add(appoinmentSessionBean);
                }
                if(doctorAmand!=null)
                {
                    appoinmentSessionBean.setId(appoinmentSchedule.get(0).getDoctorScheduleGridId());
                    appoinmentSessionBean.setSessionStartTime(doctorScheduleGridAlteration.getSessionStartTime());
                    appoinmentSessionBean.setSessionEndTime(appoinmentSchedule.get(0).getSessionEnd());
                    appoinmentSessionBean.setDate(String.valueOf(currentdate.plusDays(i)));
                    appoinmentSessionBean.setDay(appoinmentSchedule.get(0).getDayOfWeek());
                    appoinmentSessionBean.setMaxCount(doctorScheduleGridAlteration.getMaxCount());
                    doctorAppoinmentSessionList.add(appoinmentSessionBean);
                }else{
                    appoinmentSessionBean.setId(appoinmentSchedule.get(0).getDoctorScheduleGridId());
                    appoinmentSessionBean.setSessionStartTime(appoinmentSchedule.get(0).getSessionStart());
                    appoinmentSessionBean.setSessionEndTime(appoinmentSchedule.get(0).getSessionEnd());
                    appoinmentSessionBean.setDay(appoinmentSchedule.get(0).getDayOfWeek());
                    appoinmentSessionBean.setMaxCount(appoinmentSchedule.get(0).getMaxCount());
                    appoinmentSessionBean.setDate(String.valueOf(currentdate.plusDays(i)));
                    appoinmentSessionBean.setStatus("AVAILABLE");
                    doctorAppoinmentSessionList.add(appoinmentSessionBean);
                }
            });

        });
        return doctorAppoinmentSessionList;
    }

    private static List<AppointmentTimeProcessor> addAlteration(List<AppointmentTimeProcessor> old,
                                                                List<AppointmentTimeProcessor> value) {
        System.out.println("vvvvvvvvvvvvvvvvvvvv " + value.size());
        List<AppointmentTimeProcessor> newLis = new ArrayList<>();
        old.forEach(o -> newLis.add(o));
        value.forEach(v -> newLis.add(v));
        return newLis;
    }

    private static List<AppointmentTimeProcessor> addAlteration1(List<AppointmentTimeProcessor> old,
                                                                 List<AppointmentTimeProcessor> value) {
        System.out.println("vvvvvvvvvvvvvvvvvvvv " + value.size());
        List<AppointmentTimeProcessor> newLis = new ArrayList<>();
        value.forEach(val -> {
            if (val.getAbsentList() != null) {
                val.getAbsentList().forEach(a -> {
                    old.get(0).getAbsentList().add(a);
                });
            } else if (val.getAmendList() != null) {
                val.getAmendList().forEach(a -> {
                    old.get(0).getAmendList().add(a);
                });
            }
        });
        return old;
    }

    @GetMapping(value = "/schedule/{doctorDispensaryId}")
    public ResponseEntity<List<DoctorScheduleGrid>> getSchedule(@PathVariable String doctorDispensaryId){
        List<DoctorScheduleGrid> doctorScheduleGrid = doctorScheduleGridRepository
                .findByDoctorDispensaryId(Long.parseLong(doctorDispensaryId));
        return  ResponseEntity.ok().body(doctorScheduleGrid);

    }

    private static List<DoctorScheduleGrid> addSchedule(List<DoctorScheduleGrid> old,
                                                        DoctorScheduleGrid value){
        List<DoctorScheduleGrid>  newLis =new  ArrayList<>();
        old.forEach(o->newLis.add(o));
        newLis.add(value);
        return newLis;
    }

}

package lk.ehand.healthservice.controller;

import lk.ehand.healthservice.domain.*;
import lk.ehand.healthservice.exception.ResourceNotFoundException;
import lk.ehand.healthservice.repository.IDoctorDispensaryRepository;
import lk.ehand.healthservice.repository.IDoctorScheduleGridAlterationRepository;
import lk.ehand.healthservice.repository.IDoctorScheduleGridRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
public class DoctorScheduleGridController {

    private IDoctorDispensaryRepository doctorDispensaryRepository;
    private IDoctorScheduleGridRepository doctorScheduleGridRepository;
    private IDoctorScheduleGridAlterationRepository iDoctorScheduleGridAlterationRepository;

    @Autowired
    private ApplicationContext appContext;

    public DoctorScheduleGridController(IDoctorDispensaryRepository doctorDispensaryRepository,
                                        IDoctorScheduleGridRepository doctorScheduleGridRepository,
                                        IDoctorScheduleGridAlterationRepository doctorScheduleGridAlterationRepository){
        this.doctorDispensaryRepository =doctorDispensaryRepository;
        this.doctorScheduleGridRepository = doctorScheduleGridRepository;
    }

    @PostMapping(value = "/schedule/{doctorDispensaryId}")
    public ResponseEntity<DoctorScheduleGrid> saveCity(@RequestBody DoctorScheduleGrid doctorScheduleGrid,
                                       @PathVariable("doctorDispensaryId") String doctorDispensaryId
                               ){
        log.info("POST - save city :{}",doctorScheduleGrid);
       DoctorDispensary doctorDispensary= doctorDispensaryRepository.findById(Long.parseLong(doctorDispensaryId))
               .orElseThrow(()-> new ResourceNotFoundException("No matching doctor and dispensary found"));
       doctorScheduleGrid.setDoctorDispensary(doctorDispensary);

       return new ResponseEntity(doctorScheduleGridRepository.save(doctorScheduleGrid), HttpStatus.CREATED);
    }

    @GetMapping(value = "/schedule/{doctorDispensaryId}")
    public ResponseEntity<List<DoctorScheduleGrid>> getSchedule(@PathVariable String doctorDispensaryId){
        List<DoctorScheduleGrid> doctorScheduleGrid = doctorScheduleGridRepository
                .findByDoctorDispensaryId(Long.parseLong(doctorDispensaryId));
        return  ResponseEntity.ok().body(doctorScheduleGrid);

    }

    private static List<DoctorScheduleGrid> addSchedule(List<DoctorScheduleGrid> old,
                                                        DoctorScheduleGrid value){
        System.out.println("::::::::::::::::::::::::: "+old.size());
        System.out.println(">>>>>>>>>>>>>>vv "+value);
        List<DoctorScheduleGrid>  newLis =new  ArrayList<>();
        old.forEach(o->newLis.add(o));
        newLis.add(value);
        return newLis;
    }


    /**
     *
     * @param docScheduleReq
     * @param doctorDispensaryId
     * @return
     * get noOfDays from request object
     * take next
     * get day from the current date
     * get sessionStartTime from the db
     * get treshold time for given id and calculate lasttime to book
     * validate current date and time with calculated last time and add or not to avaiable session array
     *
     * loop incoming noOfDays and get each day and take session time and add to available session array
     */
    @PostMapping(value = "/schedule/{doctorDispensaryId}/sessions")
    public List<DoctorAppoinmentSession> getNextAvailableSessions(@PathVariable("doctorDispensaryId") String doctorDispensaryId,
                                                                  @RequestBody DoctorScheduleRequest request
                                                           ){
        List<DoctorAppoinmentSession> doctorAppoinmentSessions =new ArrayList<>();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Map<String,List<DoctorScheduleGrid>> doctorScheduleGridMap = new HashMap<>();

        List<DoctorScheduleGrid> doctorScheduleGridList = doctorScheduleGridRepository.
                findByDoctorDispensaryId(Long.parseLong(doctorDispensaryId));

        doctorScheduleGridList.forEach(schedule->{
            doctorScheduleGridMap.putIfAbsent(schedule.getDayOfWeek(), Arrays.asList(schedule));
            doctorScheduleGridMap.computeIfPresent(schedule.getDayOfWeek(),
                    (k,v)-> addSchedule(v,schedule));

            log.info("?????????????????????? {}",doctorScheduleGridMap.get("MONDAY").size());

        });
        int noOfDays=0;
        int incrementDateByOne =0;
        while(noOfDays < 2 ){
            if(incrementDateByOne >10){
                break;
            }
            LocalDateTime searchDate = currentDateTime.plusDays(incrementDateByOne);
            List<DoctorScheduleGrid> currentDaySchedules= doctorScheduleGridRepository.
                    findByDoctorDispensaryIdAndDayOfWeek(Long.parseLong(doctorDispensaryId),
                            searchDate.getDayOfWeek().toString());
            incrementDateByOne ++;
            currentDaySchedules.forEach(day->{
                int sessionStartHour = Integer.parseInt(day.getSessionStart().split(":")[0]);
                int sessionStartMinute = Integer.parseInt(day.getSessionStart().split(":")[1]);
                LocalDateTime scheduleTime = LocalDateTime.of(LocalDate.now(),LocalTime.of(sessionStartHour,
                        sessionStartMinute));
                log.info("currentTime :{}",currentDateTime);
                log.info("appoinmentTime :{}",scheduleTime);
                log.info("Duration :{}",Duration.between(currentDateTime,scheduleTime).getSeconds());
                if(Duration.between(currentDateTime,scheduleTime).getSeconds() >0){
                   // noOfDays = noOfDays + 1;
                    DoctorAppoinmentSession appoinmentSessionBean=
                            (DoctorAppoinmentSession) appContext.getBean("doctorSession");;
                    appoinmentSessionBean.setDate(LocalDate.now().toString());
                    appoinmentSessionBean.setDay(day.getDayOfWeek());
                    appoinmentSessionBean.setSessionStartTime(day.getSessionStart());
                    appoinmentSessionBean.setNextAppoinmentNo(10);
                    log.info(">>>>>>>>>>>>>>>>>>>>>>>> doctorApp >>>>>> "+appoinmentSessionBean.getSessionStartTime());
                    appContext.getBean("doctorSession");
                    doctorAppoinmentSessions.add(appoinmentSessionBean);
                    doctorAppoinmentSessions.forEach(d->{
                        System.out.println("NNNNN :"+d.getSessionStartTime());
                    });
                    log.info(doctorAppoinmentSessions.get(0).getSessionStartTime());

                }

            });
        }
        return doctorAppoinmentSessions;
    }


}

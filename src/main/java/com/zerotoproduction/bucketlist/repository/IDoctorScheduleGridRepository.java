package com.zerotoproduction.bucketlist.repository;

import com.zerotoproduction.bucketlist.domain.DoctorScheduleGrid;
import com.zerotoproduction.bucketlist.domain.DoctorScheduleGridAlteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public interface IDoctorScheduleGridRepository extends JpaRepository<DoctorScheduleGrid,Long> {

//    @Query(value = "SELECT ds.id,dd.DISPENSARY_ID, ds.dayOfWeek,d.NAME,c.CITY_ID,do.NAME,c.NAME,dis.NAME\n" +
//            "from doctor_schedule_grid ds inner join doctor_dispensary dd\n" +
//            "on dd.id=ds.DOCTOR_DISPENSARY_ID\n" +
//            "inner join dispensary d on dd.DISPENSARY_ID = d.DISPENSARY_ID\n" +
//            "inner join city c on d.CITY_ID=c.CITY_ID\n" +
//            "inner join doctor do on do.DOCTOR_ID =dd.DOCTOR_ID\n" +
//            "inner join distrcit dis on dis.DISTRICT_ID=c.DISTRICT_ID\n" +
//            "where ds.DOCTOR_DISPENSARY_ID=:id",
//            nativeQuery=true)

//    @Query(value = "select ds.id,ds.DOCTOR_DISPENSARY_ID,ds.sessionStart,ds.sessionEnd,ds.dayOfWeek,ds.status from doctor_schedule_grid ds " +
//            "inner join doctor_dispensary dd\n" +
//            "on dd.id=ds.DOCTOR_DISPENSARY_ID\n" +
//            "inner join dispensary d on dd.DISPENSARY_ID = d.DISPENSARY_ID\n" +
//            "inner join city c on d.CITY_ID=c.CITY_ID\n" +
//            "inner join doctor do on do.DOCTOR_ID =dd.DOCTOR_ID\n" +
//            "inner join distrcit dis on dis.DISTRICT_ID=c.DISTRICT_ID\n" +
//            "where ds.DOCTOR_DISPENSARY_ID=:id",nativeQuery = true)
//    Collection<DoctorScheduleGrid> findByDoctorDispensaryid(Long id);


    List<DoctorScheduleGrid>  findByDoctorDispensaryId(Long id);

    @Query(value = "select dsg.* from doctor_schedule_grid dsg inner join "+
            "doctor_dispensary dd on dsg.doctor_dispensary_id=dd.doctor_dispensary_id "+
            "where dd.doctor_id=:doctorId and dd.dispensary_id=:dispensaryId ", nativeQuery = true)
    List<DoctorScheduleGrid> findByDoctorAndispensary(Long doctorId,Long dispensaryId);

    //UPPER(DAYNAME(CURDATE())),UPPER(DAYNAME(CURDATE()+1)),\n" +
    //            "    UPPER(DAYNAME(CURDATE()+2))
    @Query(value = "select dsg.*,dsga.*\n" +
            "       from doctor_schedule_grid dsg inner join\n" +
            "doctor_dispensary dd on dsg.doctor_dispensary_id=dd.doctor_dispensary_id\n" +
            "inner join dispensary d on dd.dispensary_id = d.dispensary_id\n" +
            "inner join doctor d2 on dd.doctor_id = d2.doctor_id\n" +
            "left join doctor_schedule_grid_alteration dsga on dsg.id = dsga.doctor_schedule_grid_id\n" +
            "where d2.doctor_id=:doctorId and d.dispensary_id=:dispensaryId\n" +
            "and dsg.day_of_week IN :noOfdays " ,nativeQuery = true)
    List<DoctorScheduleGrid> findByDoctorAndDispensaryAndDayOfWeek(Long doctorId, Long dispensaryId, List<String> noOfdays );

    List<DoctorScheduleGrid> findByDoctorDispensaryIdAndDayOfWeek(Long id,String day);

}

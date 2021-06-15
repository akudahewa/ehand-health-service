package lk.ehand.healthservice.repository;

import lk.ehand.healthservice.domain.DoctorScheduleGridAlteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IDoctorScheduleGridAlterationRepository extends JpaRepository<DoctorScheduleGridAlteration,Long> {

    DoctorScheduleGridAlteration findByDoctorScheduleGridId(Long id);

    @Query(value = "select dsga.*\n" +
            "       from doctor_schedule_grid dsg inner join\n" +
            "doctor_dispensary dd on dsg.doctor_dispensary_id=dd.doctor_dispensary_id\n" +
            "inner join dispensary d on dd.dispensary_id = d.dispensary_id\n" +
            "inner join doctor d2 on dd.doctor_id = d2.doctor_id\n" +
            "left join doctor_schedule_grid_alteration dsga on dsg.id = dsga.doctor_schedule_grid_id\n" +
            "where d2.doctor_id=:doctorId and d.dispensary_id=:dispensaryId\n" +
            "and dsg.day_of_week IN :noOfdays " ,nativeQuery = true)
    List<DoctorScheduleGridAlteration> findByDoctorAndDispensaryAndDayOfWeek(Long doctorId, Long dispensaryId, List<String> noOfdays );
//    List<DoctorScheduleGridAlteration> findByDoctorAndDispensaryAndDayOfWeek(Long doctorId, Long dispensaryId, List<String> noOfdays );
}

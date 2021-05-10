package lk.ehand.healthservice.repository;

import lk.ehand.healthservice.domain.DoctorScheduleGrid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

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

    List<DoctorScheduleGrid> findByDoctorDispensaryId(Long id);

    List<DoctorScheduleGrid> findByDoctorDispensaryIdAndDayOfWeek(Long id,String day);

}

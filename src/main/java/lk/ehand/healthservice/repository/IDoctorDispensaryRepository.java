package lk.ehand.healthservice.repository;

import lk.ehand.healthservice.domain.DoctorDispensary;
import lk.ehand.healthservice.domain.DoctorScheduleGrid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IDoctorDispensaryRepository extends JpaRepository<DoctorDispensary,Long> {

    @Query(value = "SELECT dd.* from doctor_dispensary dd inner join dispensary d on dd.dispensary_id = d.dispensary_id\n" +
            "inner join doctor d2 on dd.doctor_id = d2.doctor_id\n" +
            "where d.city_id=1",nativeQuery = true)
    List<DoctorDispensary> findAllByDispensary(Long id);

    @Query("SELECT dd,d2,d from DoctorDispensary dd inner join Dispensary d on dd.dispensary.dispensaryId = d.dispensaryId\n" +
            "inner join Doctor d2 on dd.doctor.doctorId = d2.doctorId\n" +
            "where (:doctorId is null or d2.doctorId = :doctorId) and (:dispensaryId is null or d.dispensaryId = :dispensaryId)")
    List<DoctorDispensary> findAllByDoctorAndDispensary(@Param("doctorId") Long doctorId, @Param("dispensaryId") Long dispensaryId );

}
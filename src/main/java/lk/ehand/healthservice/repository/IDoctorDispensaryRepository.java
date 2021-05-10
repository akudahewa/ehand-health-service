package lk.ehand.healthservice.repository;

import lk.ehand.healthservice.domain.DoctorDispensary;
import lk.ehand.healthservice.domain.DoctorScheduleGrid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorDispensaryRepository extends JpaRepository<DoctorDispensary,Long> {


}
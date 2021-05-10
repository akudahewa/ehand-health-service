package lk.ehand.healthservice.repository;

import lk.ehand.healthservice.domain.DoctorScheduleGridAlteration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorScheduleGridAlterationRepository extends JpaRepository<DoctorScheduleGridAlteration,Long> {

    DoctorScheduleGridAlteration findByDoctorScheduleGridId(Long id);
}

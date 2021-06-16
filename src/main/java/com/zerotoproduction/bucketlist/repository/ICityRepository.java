package com.zerotoproduction.bucketlist.repository;

import com.zerotoproduction.bucketlist.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ICityRepository extends JpaRepository<City,Long> {

    List<City> findCitiesByDistrictId(Long id);

    @Modifying
    @Query(value = "insert into city_doctors (CITY_ID,DOCTOR_ID) VALUES (:cityId,:doctorId)", nativeQuery = true)
    @Transactional
    void updateDoctorInCity(@Param("doctorId") Long doctorId, @Param("cityId") Long cityId);
}

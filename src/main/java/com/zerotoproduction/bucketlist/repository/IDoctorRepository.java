package com.zerotoproduction.bucketlist.repository;

import com.zerotoproduction.bucketlist.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor,Long> {

}

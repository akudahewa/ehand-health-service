package com.zerotoproduction.bucketlist.repository;

import com.zerotoproduction.bucketlist.domain.DispensaryUser;
import com.zerotoproduction.bucketlist.domain.DoctorDispensary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDispensaryUserRepository extends JpaRepository<DispensaryUser,Long> {

    DispensaryUser findByUserId(String userId);
}

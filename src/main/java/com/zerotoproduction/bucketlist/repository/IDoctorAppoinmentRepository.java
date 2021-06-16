package com.zerotoproduction.bucketlist.repository;

import com.zerotoproduction.bucketlist.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorAppoinmentRepository extends JpaRepository<Transaction,Long> {
}

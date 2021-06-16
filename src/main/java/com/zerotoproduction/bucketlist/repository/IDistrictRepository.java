package com.zerotoproduction.bucketlist.repository;

import com.zerotoproduction.bucketlist.domain.District;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistrictRepository extends JpaRepository<District,Long> {
}

package com.zerotoproduction.bucketlist.repository;

import com.zerotoproduction.bucketlist.domain.Dispensary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDispensaryRepository extends JpaRepository<Dispensary,Long> {

    @Query(
            value = "SELECT * FROM dispensary d WHERE d.CITY_ID = :id",
            nativeQuery = true)
    List<Dispensary> findDispensariesByCityId(Long id);


}

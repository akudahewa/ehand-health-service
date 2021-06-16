package com.zerotoproduction.bucketlist;

import com.zerotoproduction.bucketlist.domain.BucketList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<BucketList, Long> {
}

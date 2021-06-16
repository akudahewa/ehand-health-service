package com.zerotoproduction.bucketlist.repository;

import com.zerotoproduction.bucketlist.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ITransactionRepository extends JpaRepository<Transaction,Long> {

    @Modifying
    @Query("UPDATE Transaction t set t.status = :status where t.id = :id")
    void updateTransacction(@Param("id") long id, @Param("status") int status);
}

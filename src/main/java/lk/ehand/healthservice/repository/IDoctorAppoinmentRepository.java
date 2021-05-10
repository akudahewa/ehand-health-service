package lk.ehand.healthservice.repository;

import lk.ehand.healthservice.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorAppoinmentRepository extends JpaRepository<Transaction,Long> {
}

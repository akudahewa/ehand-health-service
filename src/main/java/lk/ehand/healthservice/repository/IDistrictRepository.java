package lk.ehand.healthservice.repository;

import lk.ehand.healthservice.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistrictRepository extends JpaRepository<District,Long> {
}

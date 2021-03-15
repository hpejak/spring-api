package hr.pejak.repository;

import hr.pejak.dto.WaterConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterRepository extends JpaRepository<WaterConsumption, Long> {

    List<WaterConsumption> findByYear(Integer year);

    @Query(value = "SELECT * FROM water WHERE water.year = :year AND water.month = :month", nativeQuery = true)
    WaterConsumption findByMonthYear (Integer year, Integer month);



}

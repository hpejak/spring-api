package hr.pejak.service;

import hr.pejak.dto.WaterConsumption;
import hr.pejak.repository.WaterRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterService {


    private final WaterRepository waterRepository;


    public WaterService(final WaterRepository waterRepository) {
        this.waterRepository = waterRepository;
    }


    public List<WaterConsumption> getAllConsumption() {
        return waterRepository.findAll();
    }

    public List<WaterConsumption> getYearConsumption(Integer year) {
        return waterRepository.findByYear(year);
    }

    public WaterConsumption getMonthConsumption(@Param("year") Integer year, @Param("month") Integer month) {
        return waterRepository.findByMonthYear(year, month);
    }

}

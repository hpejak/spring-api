package hr.pejak.demo;

import hr.pejak.dto.Consumption;
import hr.pejak.dto.WaterConsumption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WaterConsumptionJdbc {
    private static final String ALL_SQL = "SELECT * FROM water";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WaterConsumptionJdbc(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WaterConsumption> findAll() {
        return jdbcTemplate.query(ALL_SQL, new BeanPropertyRowMapper<>(WaterConsumption.class));
    }

    public List<WaterConsumption> findByYear(Integer year) { return jdbcTemplate.query("SELECT * FROM water WHERE water.year = ?",
            new Object[]{year}, new BeanPropertyRowMapper<>(WaterConsumption.class));
    }

    public WaterConsumption findByYearMonth(Integer year, Integer month) {
        return jdbcTemplate.queryForObject("SELECT * FROM water WHERE water.year = ? AND water.month = ?", new Object[]{year, month},
                new BeanPropertyRowMapper<>(WaterConsumption.class));
    }

    public List<Consumption> findConsumptions() {return jdbcTemplate.query(ALL_SQL, new WaterConsumptionRowMapper()); }

}

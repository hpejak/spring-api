package hr.pejak.jdbc;

import hr.pejak.dto.Consumption;
import hr.pejak.dto.WaterConsumption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Slf4j
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

    public Integer insertWaterConsumption (WaterConsumption waterConsumption) {
        Integer response;

        try {
            final String SQL_INSERT_WATER_CONSUMPTION = "INSERT INTO water (YEAR, MONTH, CONS_STATE_REPORTED, CONS_STATE_BILL, " +
                    "CONS_STATE_FIRST_FLORE, CONS_STATE_GROUND_FLORE, CONS_STATE_YARD_HOUSE, CONSUMPTION_DISCREPANCY, BILL_AMOUNT, " +
                    "AMOUNT_FIRST_FLORE, AMOUNT_GROUND_FLORE, AMOUNT_YARD_HOUSE, AMOUNT_DISCREPANCY, AMOUNT_EXTERNAL, " +
                    "AMOUNT_COMMUNICATED, RECEIVED_EXTERNAL, READ_DATE, BILL_PAYED_DATE, CONSUMPTION_ALL, CONSUMPTION_FIRST_FLORE, " +
                    "CONSUMPTION_GROUND_FLORE, CONSUMPTION_YARD_HOUSE, CONSUMPTION_BILL, CONSUMPTION_REPORTED)  " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


            KeyHolder keyHolder = new GeneratedKeyHolder();

            PreparedStatementCreator psc = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    final PreparedStatement ps = connection.prepareStatement(SQL_INSERT_WATER_CONSUMPTION,
                        Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, waterConsumption.getYear());
                    ps.setInt(2, waterConsumption.getMonth());
                    ps.setLong(3, waterConsumption.getConsStateReported());
                    ps.setLong(4, waterConsumption.getConsStateBill());
                    ps.setFloat(5, waterConsumption.getConsStateFirstFlore());
                    ps.setFloat(6, waterConsumption.getConsStateGroundFlore());
                    ps.setFloat(7, waterConsumption.getConsumptionYardHouse());
                    ps.setFloat(8, waterConsumption.getConsumptionDiscrepancy());
                    ps.setFloat(9, waterConsumption.getBillAmount());
                    ps.setFloat(10, waterConsumption.getAmountFirstFlore());
                    ps.setFloat(11, waterConsumption.getAmountGroundFlore());
                    ps.setFloat(12, waterConsumption.getAmountYardHouse());
                    ps.setFloat(13, waterConsumption.getAmountDiscrepancy());
                    ps.setFloat(14, waterConsumption.getAmountExternal());
                    ps.setFloat(15, waterConsumption.getAmountCommunicated());
                    ps.setFloat(16, waterConsumption.getReceivedExternal());
                    ps.setDate(17, (Date) waterConsumption.getReadDate());
                    ps.setString(18, waterConsumption.getBillPayedDate());
                    ps.setLong(19, waterConsumption.getConsumptionAll());
                    ps.setFloat(20, waterConsumption.getConsumptionFirstFlore());
                    ps.setFloat(21, waterConsumption.getConsumptionGroundFlore());
                    ps.setFloat(22, waterConsumption.getConsumptionYardHouse());
                    ps.setFloat(23, waterConsumption.getConsumptionBill());
                    ps.setFloat(24, waterConsumption.getConsumptionReported());

                    return ps;
                }
            };

            response = jdbcTemplate.update(psc,keyHolder);

        } catch (Exception e) {
            log.info("PROBE 1");
            log.info(String.valueOf(e));
            response = 0;
        }
        log.info(String.valueOf(response));
        return response;
    }

    public List<Consumption> findConsumptions() {return jdbcTemplate.query(ALL_SQL, new WaterConsumptionRowMapper()); }

}

package hr.pejak.demo;

import hr.pejak.dto.Consumption;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WaterConsumptionRowMapper implements RowMapper<Consumption> {
    @Override
    public Consumption mapRow(ResultSet rs, int rowNum) throws SQLException {
        Consumption consumption = new Consumption();
        consumption.setConsumptionAll(rs.getLong("consumption_all"));
        consumption.setConsumptionBill(rs.getFloat("consumption_bill"));
        consumption.setConsumptionFirstFlore(rs.getFloat("consumption_first_flore"));
        consumption.setConsumptionGroundFlore(rs.getFloat("consumption_ground_flore"));
        consumption.setConsumptionYardHouse(rs.getFloat("consumption_yard_house"));
        return consumption;
    }
}

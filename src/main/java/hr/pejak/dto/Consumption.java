package hr.pejak.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class Consumption {

    @Column(name = "consumption_first_flore")
    private Float consumptionFirstFlore;

    @Column(name = "consumption_ground_flore")
    private Float consumptionGroundFlore;

    @Column(name = "consumption_yard_house")
    private Float consumptionYardHouse;

    @Column(name = "consumption_bill")
    private Float consumptionBill;

    @Column(name="consumption_all")
    private Long consumptionAll;
}

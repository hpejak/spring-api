package hr.pejak.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Service
@Table(name = "water")
public class WaterConsumption{

    @Id
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "cons_state_reported")
    private Long consStateReported;

    @Column(name = "cons_state_bill")
    private Long consStateBill;

    @Column(name = "cons_state_first_flore")
    private Float consStateFirstFlore;

    @Column(name = "cons_state_ground_flore")
    private Float consStateGroundFlore;

    @Column(name = "cons_state_yard_house")
    private Float consStateYardHouse;

    @Column(name = "consumption_discrepancy")
    private Float consumptionDiscrepancy;

    @Column(name = "bill_amount")
    private Float billAmount;

    @Column(name = "amount_first_flore")
    private Float amountFirstFlore;

    @Column(name = "amount_ground_flore")
    private Float amountGroundFlore;

    @Column(name = "amount_yard_house")
    private Float amountYardHouse;

    @Column(name = "amount_discrepancy")
    private Float amountDiscrepancy;

    @Column(name = "amount_external")
    private Float amountExternal;

    @Column(name = "amount_communicated")
    private Float amountCommunicated;

    @Column(name = "received_external")
    private Float receivedExternal;

    @Column(name = "read_date")
    private Date readDate;

    @Column(name = "bill_payed_date")
    private String billPayedDate;

    @Column(name = "inserted_time")
    private Date insertedTime;

    @Column(name="consumption_all")
    private Long consumptionAll;

    @Column(name = "consumption_first_flore")
    private Float consumptionFirstFlore;

    @Column(name = "consumption_ground_flore")
    private Float consumptionGroundFlore;

    @Column(name = "consumption_yard_house")
    private Float consumptionYardHouse;

    @Column(name = "consumption_bill")
    private Float consumptionBill;

    @Column(name = "consumption_reported")
    private Float consumptionReported;

}

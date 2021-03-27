package hr.pejak;

import hr.pejak.jdbc.WaterConsumptionJdbc;
import hr.pejak.dto.Consumption;
import hr.pejak.dto.WaterConsumption;
import hr.pejak.service.WaterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RestController
public class EndPoint {

    private final WaterService waterService;
    private final WaterConsumptionJdbc waterConsumptionJdbc;


    @Autowired
    public EndPoint(final WaterService waterService, final WaterConsumptionJdbc waterConsumptionJdbc) {
        this.waterService = waterService;
        this.waterConsumptionJdbc = waterConsumptionJdbc;
    }

    @GetMapping("/healthCheck")
    public ResponseEntity<String> getHealthCheck(){
        return new ResponseEntity<>("Working!",HttpStatus.OK);
    }


    @GetMapping("/waterConsumption")
    public ResponseEntity<List<WaterConsumption>> water() {
        try{
          return new ResponseEntity<>(waterConsumptionJdbc.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/consumption")
    public ResponseEntity<List<Consumption>> consumption() {
        try{
            return new ResponseEntity<>(waterConsumptionJdbc.findConsumptions(), HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findYearConsumption/{year}")
    public ResponseEntity<List<WaterConsumption>> jdbcYearWaterConsumption(@PathVariable("year") Integer year){
        try {
            return new ResponseEntity<>(waterConsumptionJdbc.findByYear(year), HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findMonthConsumption/{year}/{month}")
    public ResponseEntity<WaterConsumption> jdbcMonthWaterConsumption(@PathVariable("year") Integer year,
                                                                     @PathVariable("month") Integer month) {
        try{
            return new ResponseEntity<>(waterConsumptionJdbc.findByYearMonth(year, month),HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity<>(WaterConsumption.builder().build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getFullConsumption")
    public ResponseEntity<List<WaterConsumption>> getFullConsumption() {

        try {
            List<WaterConsumption> waterConsumptions = new ArrayList<>(waterService.getAllConsumption());
            return new ResponseEntity<>(waterConsumptions, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getYearConsumption/{year}")
    public ResponseEntity<List<WaterConsumption>> getYearConsumption(@PathVariable("year") Integer year) {

        try {
            List<WaterConsumption> waterConsumptions = new ArrayList<>(waterService.getYearConsumption(year));
            return new ResponseEntity<>(waterConsumptions, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMonthConsumption/{year}/{month}")
    public ResponseEntity<WaterConsumption> getMonthConsumption(@PathVariable("year") Integer year, @PathVariable("month") Integer month) {

        try {
            WaterConsumption waterConsumptions = waterService.getMonthConsumption(year, month);
            return new ResponseEntity<>(waterConsumptions, HttpStatus.OK);

        } catch (Exception e) {
            log.info(e.toString());
            return new ResponseEntity<>(WaterConsumption.builder().build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/healthCheck")
    public ResponseEntity<String> postHealthCheck(){
        return new ResponseEntity<>("Working!",HttpStatus.OK);
    }

    @PostMapping("/insertWaterConsumption")
    public ResponseEntity<Integer> insertWaterConsumption(@RequestBody WaterConsumption waterConsumption ) {
        try {
            return new ResponseEntity<>(waterConsumptionJdbc.insertWaterConsumption(waterConsumption), HttpStatus.OK);
        } catch ( Exception e) {
            log.info(e.toString());
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
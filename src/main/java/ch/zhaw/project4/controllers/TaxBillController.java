package ch.zhaw.project4.controllers;

import ch.zhaw.project4.entities.TaxBill;
import ch.zhaw.project4.entities.dto.TaxBillDTO;
import ch.zhaw.project4.services.TaxBillService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v1/tax-bills")
public class TaxBillController {


    private final TaxBillService taxBillService;

    public TaxBillController(TaxBillService taxBillService) {
        this.taxBillService = taxBillService;
    }

    @GetMapping()
    public ResponseEntity<List<TaxBill>> getAllTaxBills() {
        List<TaxBill> taxBills = taxBillService.getAllTaxBills();
        if (taxBills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taxBills, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaxBill> getTaxBill(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(taxBillService.getTaxBill(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/create-taxBill")
    public ResponseEntity<String> createTaxBill(@RequestBody @Valid TaxBillDTO taxBillDTO) {
        try {
            taxBillService.createTaxBill(taxBillDTO);
            return new ResponseEntity<>("Successfully created Tax Bill", HttpStatus.OK);
        } catch (ParseException | NoSuchElementException e) {
            if (e instanceof ParseException) {
                return new ResponseEntity<>("Error when parsing Date", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("No Customer with that id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package ch.zhaw.project4.services;

import ch.zhaw.project4.entities.Customer;
import ch.zhaw.project4.entities.TaxBill;
import ch.zhaw.project4.entities.dto.TaxBillDTO;
import ch.zhaw.project4.repositories.TaxBillRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaxBillService {

    private final TaxBillRepository taxBillRepository;
    private final CustomerService customerService;

    public TaxBillService(TaxBillRepository taxBillRepository, CustomerService customerService) {
        this.taxBillRepository = taxBillRepository;
        this.customerService = customerService;
    }

    public List<TaxBill> getAllTaxBills() {
        return taxBillRepository.findAll();
    }

    public TaxBill getTaxBill(Long id) {
        return taxBillRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("No Tax Bill found with that id");
        });
    }

    public TaxBill createTaxBill(TaxBillDTO taxBillDTO) throws ParseException, NoSuchElementException {
        TaxBill taxBill = new TaxBill();
        taxBill.setAmount(taxBillDTO.getAmount());
        taxBill.setTaxYear(taxBillDTO.getTaxYear());
        Customer customer = customerService.getUserById(taxBillDTO.getCustomerId());
        taxBill.setCustomer(customer);
        Date created = new Date();
        Long createdLong = created.getTime();
        taxBill.setCreatedDate(createdLong);
        String dueDate = taxBillDTO.getDueDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = format.parse(dueDate);
        Long dueDateLong = parsedDate.getTime();
        taxBill.setDueDate(dueDateLong);
        taxBillRepository.save(taxBill);
        return taxBill;
    }
}

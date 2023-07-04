package ch.zhaw.project4.controllers;

import ch.zhaw.project4.entities.TaxConsultant;
import ch.zhaw.project4.entities.dto.ConsultantCustomerDTO;
import ch.zhaw.project4.entities.Customer;
import ch.zhaw.project4.entities.dto.UserDTO;
import ch.zhaw.project4.services.CustomerService;
import ch.zhaw.project4.services.TaxConsultantService;
import ch.zhaw.project4.utils.UserUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerController implements BaseUserController<Customer, UserDTO> {

    private final CustomerService customerService;
    private final TaxConsultantService taxConsultantService;
    private final UserUtils userUtils;

    public CustomerController(CustomerService customerService, TaxConsultantService taxConsultantService, UserUtils userUtils) {
        this.customerService = customerService;
        this.taxConsultantService = taxConsultantService;
        this.userUtils = userUtils;
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<Customer>> getAllUsers() {
        List<Customer> customers = customerService.getAllUsers();
        if (customers != null) {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getUserById(@PathVariable(value = "id") long id) {
        try {
            return new ResponseEntity<>(customerService.getUserById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @PostMapping(value = "/create-customer")
    public ResponseEntity<String> addUser(@RequestBody @Valid UserDTO userDTO) {
        Customer customer = new Customer();
        customer.setFirstName(userDTO.getFirstName());
        customer.setLastName(userDTO.getLastName());
        try {
            customerService.addUser(customer);
            return new ResponseEntity<>("Successfully created new Customer", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not created Customer" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/add-consultantCustomerCooperation")
    public ResponseEntity<TaxConsultant> addConsultantCustomerCooperation(@RequestBody @Valid ConsultantCustomerDTO consultantCustomerDTO) {
        TaxConsultant taxConsultant = customerService.addConsultantCustomerCooperation(consultantCustomerDTO);
        if (taxConsultant != null) {
            return new ResponseEntity<>(taxConsultant, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/update-consultantCustomerCooperation")
    public ResponseEntity<TaxConsultant> updateConsultantCustomerCooperation(@RequestBody @Valid ConsultantCustomerDTO consultantCustomerDTO) {
        Customer customer = customerService.getUserById(consultantCustomerDTO.getCustomerId());
        if (customer == null || customer.getTaxConsultant() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (customer.getTaxConsultant().getId() == consultantCustomerDTO.getTaxConsultantId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TaxConsultant taxConsultant = taxConsultantService.getUserById(consultantCustomerDTO.getTaxConsultantId());
        customer.setTaxConsultant(taxConsultant);
        customerService.saveUser(customer);
        taxConsultantService.saveUser(taxConsultant);
        return new ResponseEntity<>(taxConsultant, HttpStatus.OK);
    }

    @PostMapping(value = "/add-like")
    public ResponseEntity<String> addLike(@RequestBody @Valid ConsultantCustomerDTO consultantCustomerDTO) {
        try {
            Customer customer = customerService.getUserById(consultantCustomerDTO.getCustomerId());
            TaxConsultant taxConsultant = taxConsultantService.getUserById(consultantCustomerDTO.getTaxConsultantId());
            // check if already liked here
            if (customerService.isAlreadyLiked(customer.getId(), taxConsultant.getId()).isPresent()) {
                return new ResponseEntity<>("Already liked!", HttpStatus.BAD_REQUEST);
            }
            else {
                customer.addLikedTaxConsultants(taxConsultant);
                taxConsultant.addLikes(customer);
                taxConsultant.incrementLikeCount();
                taxConsultantService.saveUser(taxConsultant);
                userUtils.setPopularity(taxConsultant);
            }
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("no Tax Consultant or Customer found with respective id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Like added successfully!", HttpStatus.OK);
    }

}

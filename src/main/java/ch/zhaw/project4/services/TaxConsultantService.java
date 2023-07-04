package ch.zhaw.project4.services;

import ch.zhaw.project4.entities.Customer;
import ch.zhaw.project4.entities.TaxConsultant;
import ch.zhaw.project4.entities.dto.ConsultantCustomerDTO;
import ch.zhaw.project4.repositories.TaxConsultantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaxConsultantService implements BaseUserService<TaxConsultant> {

    private final TaxConsultantRepository taxConsultantRepository;

    public TaxConsultantService(TaxConsultantRepository taxConsultantRepository) {
        this.taxConsultantRepository = taxConsultantRepository;
    }

    @Override
    public List<TaxConsultant> getAllUsers() {
        List<TaxConsultant> taxConsultants = taxConsultantRepository.findAll();
        if (!taxConsultants.isEmpty()) {
            return taxConsultants;
        }
        return null;
    }

    @Override
    public TaxConsultant getUserById(Long id){
        return taxConsultantRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("No TaxConsultant found with that id");
        });
    }

    @Override
    public TaxConsultant addUser(TaxConsultant taxConsultant) {
        return taxConsultantRepository.save(taxConsultant);
    }

    @Override
    public TaxConsultant saveUser(TaxConsultant taxConsultant) {
        return taxConsultantRepository.save(taxConsultant);
    }

}

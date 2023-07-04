package ch.zhaw.project4.repositories;

import ch.zhaw.project4.entities.TaxBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxBillRepository extends JpaRepository<TaxBill, Long> {

}

package ch.zhaw.project4.repositories;

import ch.zhaw.project4.entities.TaxConsultant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxConsultantRepository extends JpaRepository<TaxConsultant, Long> {

}

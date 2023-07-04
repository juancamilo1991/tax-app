package ch.zhaw.project4.repositories;

import ch.zhaw.project4.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM tax_consultant_likes tcl INNER JOIN customers c ON tcl.customer_id = c.id AND c.id = :customerId AND tcl.consultant_id = :consultantId",
            nativeQuery = true)
    Optional<Customer> findByAlreadyLiked(@Param("customerId") Long customerId,
                                      @Param("consultantId") Long consultantId);
}

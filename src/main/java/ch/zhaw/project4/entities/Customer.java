package ch.zhaw.project4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;


@Entity
@Table(name = "customers")
public class Customer extends User {

    @ManyToOne
    @JoinColumn(name = "tax_consultant_id")
    private TaxConsultant taxConsultant;
    @ManyToMany
    @JoinTable(
            name = "tax_consultant_likes",
            joinColumns = @JoinColumn(name = "customerId"),
            inverseJoinColumns = @JoinColumn(name = "consultantId")
    )
    private Set<TaxConsultant> likedTaxConsultants;


    public Customer() {};

    public Customer(String firstName, String lastName, TaxConsultant taxConsultant) {
        super(firstName, lastName);
        this.taxConsultant = taxConsultant;
    }

    public TaxConsultant getTaxConsultant() {
        return taxConsultant;
    }

    public void setTaxConsultant(TaxConsultant taxConsultant) {
        this.taxConsultant = taxConsultant;
    }

    public Set<TaxConsultant> getLikedTaxConsultants() {
        return likedTaxConsultants;
    }

    public void setLikedTaxConsultants(Set<TaxConsultant> likedTaxConsultants) {
        this.likedTaxConsultants = likedTaxConsultants;
    }

    public void addLikedTaxConsultants(TaxConsultant taxConsultant) {
        likedTaxConsultants.add(taxConsultant);
    }
}

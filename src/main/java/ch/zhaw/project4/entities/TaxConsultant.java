package ch.zhaw.project4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "TaxConsultants")
public class TaxConsultant extends User {

    @OneToMany(mappedBy = "taxConsultant")
    private Set<Customer> customers;
    @ManyToMany(mappedBy = "likedTaxConsultants")
    private Set<Customer> customerLikes;
    private int customerLikesCount = 0;
    private Popularity popularity = Popularity.LOW;


    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public TaxConsultant() {};

    public TaxConsultant(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @JsonIgnore
    public Set<Customer> getCustomerLikes() {
        return customerLikes;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public void setCustomerLikes(Set<Customer> customerLikes) {
        this.customerLikes = customerLikes;
    }

    @JsonIgnore
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public void incrementLikeCount() {
        customerLikesCount += 1;
    }

    public void addLikes(Customer customer) {
        customerLikes.add(customer);
    }

    public int getCustomerLikesCount() {
        return customerLikesCount;
    }


}

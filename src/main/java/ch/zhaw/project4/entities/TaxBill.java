package ch.zhaw.project4.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TaxBills")
public class TaxBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    private int amount;
    private long dueDate;
    private long createdDate;
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;
    private int taxYear;


    public TaxBill () {};


    public TaxBill(Long id, Customer customer, int amount, long dueDate, long createdDate, int taxYear) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
        this.dueDate = dueDate;
        this.taxYear = taxYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(int taxYear) {
        this.taxYear = taxYear;
    }
}

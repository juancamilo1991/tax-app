package ch.zhaw.project4.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaxBillDTO {

    @NotNull(message = "customer id must not be empty!")
    private Long customerId;
    @NotNull(message = "amount must not be empty!")
    private int amount;
    @NotBlank(message = "due date must not be empty!")
    private String dueDate;
    @NotNull(message = "tax year must not be empty!")
    private int taxYear;

    public TaxBillDTO() {}

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(int taxYear) {
        this.taxYear = taxYear;
    }
}

package ch.zhaw.project4.entities.dto;

import jakarta.validation.constraints.NotNull;

public class ConsultantCustomerDTO {

    @NotNull(message = "customer id must not be empty!")
    private Long customerId;
    @NotNull(message = "tax consultant id must not be empty!")
    private Long taxConsultantId;


    public ConsultantCustomerDTO() {};

    public ConsultantCustomerDTO(Long customerId, Long taxConsultantId) {
        this.customerId = customerId;
        this.taxConsultantId = taxConsultantId;
    }

    public Long getTaxConsultantId() {
        return taxConsultantId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}

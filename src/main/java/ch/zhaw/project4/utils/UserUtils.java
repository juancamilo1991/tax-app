package ch.zhaw.project4.utils;

import ch.zhaw.project4.entities.Customer;
import ch.zhaw.project4.entities.Popularity;
import ch.zhaw.project4.entities.TaxConsultant;
import ch.zhaw.project4.services.CustomerService;
import ch.zhaw.project4.services.TaxConsultantService;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    private final TaxConsultantService taxConsultantService;

    public UserUtils(TaxConsultantService taxConsultantService) {
        this.taxConsultantService = taxConsultantService;
    }

    public void setPopularity(TaxConsultant taxConsultant) {
        int likesSum = taxConsultant.getCustomerLikesCount();

        if (likesSum <= Popularity.LOW.getLevelOfPop()) {
            taxConsultant.setPopularity(Popularity.LOW);
        }
        if (likesSum > Popularity.LOW.getLevelOfPop() && likesSum <= Popularity.MEDIUM.getLevelOfPop()) {
            taxConsultant.setPopularity(Popularity.MEDIUM);
        }
        else {
            taxConsultant.setPopularity(Popularity.HIGH);
        }
        taxConsultantService.saveUser(taxConsultant);
    }
}

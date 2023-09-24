package payment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ServiceCharge {
    @Id
    private Long id;
    private Long date;
    private double amount;

    public ServiceCharge(Long date, double amount) {
        this.date = date;
        this.amount = amount;
    }
}


package payment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SalesReceipt {
    @Id
    private Long id;

    public SalesReceipt(Long date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    private Long date;
    private double amount;
}

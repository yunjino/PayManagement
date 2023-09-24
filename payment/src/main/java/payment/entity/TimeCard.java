package payment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TimeCard {
    @Id
    private Long id;

    public TimeCard(Long date, double hours) {
        this.date = date;
        this.hours = hours;
    }

    private Long date;
    private double hours;
}

package payment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class TimeCard {
    private Date date;
    private double hours;
}

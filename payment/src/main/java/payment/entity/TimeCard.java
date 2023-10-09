package payment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TimeCard {
    private Long date;
    private double hours;
}

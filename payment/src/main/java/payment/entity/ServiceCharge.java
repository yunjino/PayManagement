package payment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServiceCharge {
    private Long date;
    private double amount;
}


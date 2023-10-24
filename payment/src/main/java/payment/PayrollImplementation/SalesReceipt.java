package payment.PayrollImplementation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class SalesReceipt {
    private Date date;
    private double amount;
}

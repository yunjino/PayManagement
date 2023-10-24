package payment.PayrollDomain;

import java.util.Date;

public interface PaymentSchedule {
    boolean isPayDay(Date date);
}

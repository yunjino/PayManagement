package payment.schedule;

import java.util.Date;

public interface PaymentSchedule {
    boolean isPayDay(Date date);
}

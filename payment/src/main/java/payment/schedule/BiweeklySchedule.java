package payment.schedule;

import java.util.Date;

public class BiweeklySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDay(Date date) {
        return false;
    }
}

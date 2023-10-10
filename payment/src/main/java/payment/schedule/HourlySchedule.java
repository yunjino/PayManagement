package payment.schedule;

import java.util.Date;

public class HourlySchedule implements PaymentSchedule {
    //TODO
    @Override
    public boolean isPayDay(Date date) {
        return false;
    }
}

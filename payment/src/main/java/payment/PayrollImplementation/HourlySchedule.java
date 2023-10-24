package payment.PayrollImplementation;

import payment.PayrollDomain.PaymentSchedule;

import java.util.Date;

public class HourlySchedule implements PaymentSchedule {
    @Override
    public boolean isPayDay(Date date) {
        return false;
    }
}

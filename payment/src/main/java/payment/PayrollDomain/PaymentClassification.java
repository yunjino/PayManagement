package payment.PayrollDomain;

import java.util.Date;

public interface PaymentClassification {
    double calculatePay(Paycheck pc);

    default boolean isInPayPeriod(Date theDate, Paycheck paycheck) {
        Date payPeriodEndDate = paycheck.getPayPeriodEndDate();
        Date payPeriodStartDate = paycheck.getPayPeriodStartDate();
        return !(theDate.before(payPeriodStartDate) || theDate.after(payPeriodEndDate));
    }
}

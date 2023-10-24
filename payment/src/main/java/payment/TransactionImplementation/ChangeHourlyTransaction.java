package payment.TransactionImplementation;

import payment.AbstractTransactions.ChangeClassificationTransaction;
import payment.PayrollImplementation.HourlyClassification;
import payment.PayrollDomain.PaymentClassification;
import payment.PayrollDomain.PaymentSchedule;
import payment.PayrollImplementation.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private final double hourlyRate;

    public ChangeHourlyTransaction(Integer empId, double hourlyRate) {
        super(empId);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}

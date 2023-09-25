package payment.changeEmployee;

import payment.classification.HourlyClassification;
import payment.classification.PaymentClassification;
import payment.schedule.PaymentSchedule;
import payment.schedule.WeeklySchedule;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
    private final double hourlyRate;

    public ChangeHourlyTransaction(Integer empId, double hourlyRate) {
        super(empId);
        this.hourlyRate = hourlyRate;
    }

    @Override
    PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new WeeklySchedule();
    }
}

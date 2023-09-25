package payment.changeEmployee;

import payment.classification.CommissionedClassification;
import payment.classification.PaymentClassification;
import payment.schedule.BiweeklySchedule;
import payment.schedule.PaymentSchedule;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
    private final double salary;
    private final double commissionRate;

    public ChangeCommissionedTransaction(Integer empId, double salary, double commissionRate) {
        super(empId);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}

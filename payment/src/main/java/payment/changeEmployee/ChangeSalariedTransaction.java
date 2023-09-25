package payment.changeEmployee;

import payment.classification.PaymentClassification;
import payment.classification.SalariedClassification;
import payment.schedule.MonthlySchedule;
import payment.schedule.PaymentSchedule;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private final double salary;

    public ChangeSalariedTransaction(Integer empId, double salary) {
        super(empId);
        this.salary = salary;
    }

    @Override
    PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}

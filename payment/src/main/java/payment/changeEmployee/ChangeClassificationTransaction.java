package payment.changeEmployee;

import payment.classification.PaymentClassification;
import payment.entity.Employee;
import payment.schedule.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(Integer empId) {
        super(empId);
    }

    @Override
    void change(Employee e) {
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
    }

    abstract PaymentClassification getClassification();

    abstract PaymentSchedule getSchedule();
}

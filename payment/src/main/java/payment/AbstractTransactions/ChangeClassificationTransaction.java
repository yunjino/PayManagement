package payment.AbstractTransactions;

import payment.PayrollDomain.PaymentClassification;
import payment.PayrollDomain.Employee;
import payment.PayrollDomain.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(Integer empId) {
        super(empId);
    }

    @Override
    public void change(Employee e) {
        e.setClassification(getClassification());
        e.setSchedule(getSchedule());
    }

    protected abstract PaymentClassification getClassification();

    protected abstract PaymentSchedule getSchedule();
}

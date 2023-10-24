package payment.TransactionImplementation;

import payment.AbstractTransactions.ChangeClassificationTransaction;
import payment.PayrollDomain.PaymentClassification;
import payment.PayrollImplementation.SalariedClassification;
import payment.PayrollImplementation.MonthlySchedule;
import payment.PayrollDomain.PaymentSchedule;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private final double salary;

    public ChangeSalariedTransaction(Integer empId, double salary) {
        super(empId);
        this.salary = salary;
    }

    @Override
    public PaymentClassification getClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }
}

package payment.TransactionImplementation;

import payment.AbstractTransactions.ChangeClassificationTransaction;
import payment.PayrollImplementation.CommissionedClassification;
import payment.PayrollDomain.PaymentClassification;
import payment.PayrollImplementation.BiweeklySchedule;
import payment.PayrollDomain.PaymentSchedule;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
    private final double salary;
    private final double commissionRate;

    public ChangeCommissionedTransaction(Integer empId, double salary, double commissionRate) {
        super(empId);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}

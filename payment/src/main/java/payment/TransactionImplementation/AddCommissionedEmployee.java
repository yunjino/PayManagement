package payment.TransactionImplementation;

import payment.AbstractTransactions.AddEmployeeTransaction;
import payment.PayrollImplementation.CommissionedClassification;
import payment.PayrollDomain.PaymentClassification;
import payment.PayrollImplementation.BiweeklySchedule;
import payment.PayrollDomain.PaymentSchedule;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
    private final double salary;
    private final double commissionRate;

    public AddCommissionedEmployee(Integer empId, String name, String address, double salary, double commissionRate) {
        super(empId, name, address);
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

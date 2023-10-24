package payment.TransactionImplementation;

import payment.AbstractTransactions.AddEmployeeTransaction;
import payment.PayrollDomain.PaymentClassification;
import payment.PayrollImplementation.SalariedClassification;
import payment.PayrollImplementation.MonthlySchedule;
import payment.PayrollDomain.PaymentSchedule;

public class AddSalariedEmployee extends AddEmployeeTransaction {
    private final double salary;

    public AddSalariedEmployee(Integer empId, String name, String address, double salary) {
        super(empId, name, address);
        this.salary = salary;
    }

    @Override
    public PaymentClassification getClassification(){
        return new SalariedClassification(salary);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

}

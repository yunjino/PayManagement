package payment.AbstractTransactions;

import payment.PayrollDatabase.PaymentDatabase;
import payment.TransactionApplication.Transaction;
import payment.PayrollDomain.Affiliation;
import payment.PayrollImplementation.NoAffiliation;
import payment.PayrollDomain.PaymentClassification;
import payment.PayrollDomain.Employee;
import payment.PayrollImplementation.HoldMethod;
import payment.PayrollDomain.PaymentMethod;
import payment.PayrollDomain.PaymentSchedule;

public abstract class AddEmployeeTransaction implements Transaction {
    private final Integer empId;
    private final String name;
    private final String address;

    protected AddEmployeeTransaction(Integer empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    @Override
    public void execute() {
        PaymentClassification paymentClassification = getClassification();
        PaymentSchedule paymentSchedule = getSchedule();
        PaymentMethod paymentMethod = new HoldMethod();
        Affiliation affiliation = new NoAffiliation();

        Employee employee = Employee.builder()
                .empId(empId)
                .name(name)
                .address(address)
                .classification(paymentClassification)
                .schedule(paymentSchedule)
                .method(paymentMethod)
                .affiliation(affiliation)
                .build();
        PaymentDatabase.addEmployee(empId, employee);
    }

    protected abstract PaymentClassification getClassification();

    protected abstract PaymentSchedule getSchedule();
}

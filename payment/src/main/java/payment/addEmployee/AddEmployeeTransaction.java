package payment.addEmployee;

import payment.PaymentDatabase;
import payment.Transaction;
import payment.affiliation.Affiliation;
import payment.affiliation.NoAffiliation;
import payment.classification.PaymentClassification;
import payment.entity.Employee;
import payment.method.HoldMethod;
import payment.method.PaymentMethod;
import payment.schedule.PaymentSchedule;

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

    abstract PaymentClassification getClassification();

    abstract PaymentSchedule getSchedule();
}

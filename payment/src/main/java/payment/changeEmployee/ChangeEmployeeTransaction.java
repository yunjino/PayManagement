package payment.changeEmployee;

import payment.Transaction;
import payment.PaymentDatabase;
import payment.entity.Employee;

public abstract class ChangeEmployeeTransaction implements Transaction {
    private final Integer empId;

    public ChangeEmployeeTransaction(Integer empId) {
        this.empId = empId;
    }

    @Override
    public void execute() throws Exception {
        Employee employee = PaymentDatabase.getEmployee(empId);
        change(employee);
    }

    abstract void change(Employee e);
}

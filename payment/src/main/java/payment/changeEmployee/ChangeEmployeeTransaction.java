package payment.changeEmployee;

import org.springframework.beans.factory.annotation.Autowired;
import payment.Transaction;
import payment.PayrollDatabase;
import payment.entity.Employee;

public abstract class ChangeEmployeeTransaction implements Transaction {
    private final Integer empId;

    public ChangeEmployeeTransaction(Integer empId) {
        this.empId = empId;
    }

    @Override
    public void execute() throws Exception {
        Employee employee = PayrollDatabase.getEmployee(empId);
        change(employee);
    }

    abstract void change(Employee e);
}

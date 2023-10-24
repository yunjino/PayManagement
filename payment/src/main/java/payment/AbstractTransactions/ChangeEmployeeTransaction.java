package payment.AbstractTransactions;

import payment.TransactionApplication.Transaction;
import payment.PayrollDatabase.PaymentDatabase;
import payment.PayrollDomain.Employee;

public abstract class ChangeEmployeeTransaction implements Transaction {
    private final Integer empId;

    public ChangeEmployeeTransaction(Integer empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        Employee employee = PaymentDatabase.getEmployee(empId);
        change(employee);
    }

    protected abstract void change(Employee e);
}

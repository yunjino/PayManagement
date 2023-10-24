package payment.TransactionImplementation;

import payment.TransactionApplication.Transaction;
import payment.PayrollDatabase.PaymentDatabase;

public class DeleteEmployeeTransaction implements Transaction {
    private final Integer empId;

    public DeleteEmployeeTransaction(Integer empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        PaymentDatabase.deleteEmployee(empId);
    }
}

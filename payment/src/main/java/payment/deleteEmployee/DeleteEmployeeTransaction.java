package payment.deleteEmployee;

import payment.Transaction;
import payment.PaymentDatabase;

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

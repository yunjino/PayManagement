package payment.deleteEmployee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.Transaction;
import payment.PayrollDatabase;

@Service
public class DeleteEmployeeTransaction implements Transaction {
    private final Integer empId;

    public DeleteEmployeeTransaction(Integer empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        PayrollDatabase.deleteEmployee(empId);
    }
}

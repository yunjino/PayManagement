package payment.deleteEmployee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.Transaction;
import payment.repository.EmployeeRepository;

@Service
public class DeleteEmployeeTransaction implements Transaction {
    private final Integer empId;

    @Autowired
    private EmployeeRepository employeeRepository;

    public DeleteEmployeeTransaction(Integer empId) {
        this.empId = empId;
    }

    @Override
    public void execute() {
        employeeRepository.delete(empId);
    }
}

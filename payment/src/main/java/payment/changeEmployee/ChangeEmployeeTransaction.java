package payment.changeEmployee;

import org.springframework.beans.factory.annotation.Autowired;
import payment.Transaction;
import payment.entity.Employee;
import payment.repository.EmployeeRepository;

public abstract class ChangeEmployeeTransaction implements Transaction {
    private final Integer empId;
    @Autowired
    private EmployeeRepository employeeRepository;

    public ChangeEmployeeTransaction(Integer empId) {
        this.empId = empId;
    }

    @Override
    public void execute() throws Exception {
        Employee employee = employeeRepository.getOne(empId);
        change(employee);
    }

    abstract void change(Employee e);
}

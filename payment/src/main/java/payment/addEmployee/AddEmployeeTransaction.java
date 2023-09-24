package payment.addEmployee;

import org.springframework.beans.factory.annotation.Autowired;
import payment.Transaction;
import payment.entity.Employee;
import payment.repository.EmployeeRepository;

public abstract class AddEmployeeTransaction implements Transaction {
    private final Integer empId;
    private final String name;
    private final String address;

    @Autowired
    private EmployeeRepository employeeRepository;

    protected AddEmployeeTransaction(Integer empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }

    @Override
    public void execute() {
        Employee employee = new Employee(empId, name, address);
        employeeRepository.save(employee);
    }
}

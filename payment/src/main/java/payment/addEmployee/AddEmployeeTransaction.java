package payment.addEmployee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment.Transaction;
import payment.classification.PaymentClassification;
import payment.entity.Employee;
import payment.method.HoldMethod;
import payment.method.PaymentMethod;
import payment.repository.EmployeeRepository;
import payment.schedule.PaymentSchedule;

@Service
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
        PaymentClassification paymentClassification = getClassification();
        PaymentSchedule paymentSchedule = getSchedule();
        PaymentMethod paymentMethod = new HoldMethod();
        Employee employee = new Employee(empId, name, address, paymentClassification, paymentSchedule, paymentMethod);
        employeeRepository.save(employee);
    }

    abstract PaymentClassification getClassification();
    abstract PaymentSchedule getSchedule();
}

package payment.addEmployee;

import org.springframework.stereotype.Service;
import payment.classification.PaymentClassification;
import payment.classification.SalariedClassification;
import payment.schedule.MonthlySchedule;
import payment.schedule.PaymentSchedule;

@Service
public class AddSalariedEmployee extends AddEmployeeTransaction {
    private final double salary;

    protected AddSalariedEmployee(Integer empId, String name, String address, double salary) {
        super(empId, name, address);
        this.salary = salary;
    }

    @Override
    PaymentClassification getClassification(){
        return new SalariedClassification(salary);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

}

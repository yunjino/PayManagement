package payment.addEmployee;

import payment.classification.CommissionedClassification;
import payment.classification.PaymentClassification;
import payment.schedule.BiweeklySchedule;
import payment.schedule.PaymentSchedule;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
    private final double salary;
    private final double commissionRate;

    public AddCommissionedEmployee(Integer empId, String name, String address, double salary, double commissionRate) {
        super(empId, name, address);
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    @Override
    PaymentClassification getClassification() {
        return new CommissionedClassification(salary, commissionRate);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }
}

package payment.addEmployee;

import payment.classification.HourlyClassification;
import payment.classification.PaymentClassification;
import payment.schedule.HourlySchedule;
import payment.schedule.PaymentSchedule;

public class AddHourlyEmployee extends AddEmployeeTransaction {
    private final double hourlyRate;

    protected AddHourlyEmployee(Integer empId, String name, String address, double hourlyRate) {
        super(empId, name, address);
        this.hourlyRate = hourlyRate;
    }

    @Override
    PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    PaymentSchedule getSchedule() {
        return new HourlySchedule();
    }
}

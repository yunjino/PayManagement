package payment.TransactionImplementation;

import payment.AbstractTransactions.AddEmployeeTransaction;
import payment.PayrollImplementation.HourlyClassification;
import payment.PayrollDomain.PaymentClassification;
import payment.PayrollImplementation.HourlySchedule;
import payment.PayrollDomain.PaymentSchedule;

public class AddHourlyEmployee extends AddEmployeeTransaction {
    private final double hourlyRate;

    public AddHourlyEmployee(Integer empId, String name, String address, double hourlyRate) {
        super(empId, name, address);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public PaymentClassification getClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    public PaymentSchedule getSchedule() {
        return new HourlySchedule();
    }
}

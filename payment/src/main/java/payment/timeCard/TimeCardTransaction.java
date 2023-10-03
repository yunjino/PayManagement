package payment.timeCard;

import payment.Transaction;
import payment.PaymentDatabase;
import payment.classification.HourlyClassification;
import payment.classification.PaymentClassification;
import payment.entity.Employee;
import payment.entity.TimeCard;

public class TimeCardTransaction implements Transaction {
    private final Integer empId;
    private final long date;
    private final double hours;

    public TimeCardTransaction(Integer empId, long date, double hours) {
        this.empId = empId;
        this.date = date;
        this.hours = hours;
    }

    @Override
    public void execute() throws Exception {
        Employee employee = PaymentDatabase.getEmployee(empId);
        if (employee != null) {
            PaymentClassification paymentClassification = employee.getClassification();
            if (paymentClassification instanceof HourlyClassification hourlyClassification) {
                hourlyClassification.addTimeCard(new TimeCard(date, hours));
            } else {
                throw new Exception("Tried to add timecard to non-hourly employee");
            }
        } else {
            throw new Exception("No such employee");
        }
    }
}

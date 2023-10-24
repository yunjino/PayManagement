package payment.TransactionImplementation;

import payment.TransactionApplication.Transaction;
import payment.PayrollDatabase.PaymentDatabase;
import payment.PayrollImplementation.HourlyClassification;
import payment.PayrollDomain.PaymentClassification;
import payment.PayrollDomain.Employee;
import payment.PayrollImplementation.TimeCard;

import java.util.Date;

public class TimeCardTransaction implements Transaction {
    private final Integer empId;
    private final Date date;
    private final double hours;

    public TimeCardTransaction(Integer empId, Date date, double hours) {
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
                hourlyClassification.addTimeCard(TimeCard.builder().hours(hours).date(date).build());
            } else {
                throw new Exception("Tried to add timecard to non-hourly employee");
            }
        } else {
            throw new Exception("No such employee");
        }
    }
}

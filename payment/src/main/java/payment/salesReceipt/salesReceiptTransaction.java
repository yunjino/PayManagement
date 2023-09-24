package payment.salesReceipt;

import org.springframework.beans.factory.annotation.Autowired;
import payment.Transaction;
import payment.classification.CommissionedClassification;
import payment.classification.HourlyClassification;
import payment.classification.PaymentClassification;
import payment.entity.Employee;
import payment.entity.SalesReceipt;
import payment.repository.EmployeeRepository;

public class salesReceiptTransaction implements Transaction {
    private final Long date;
    private final double amount;
    private final Integer empId;
    @Autowired
    private EmployeeRepository employeeRepository;

    public salesReceiptTransaction(Long date, double amount, Integer empId) {
        this.date = date;
        this.amount = amount;
        this.empId = empId;
    }

    @Override
    public void execute() throws Exception {
        Employee employee = employeeRepository.findOne(empId);
        if (employee != null) {
            PaymentClassification paymentClassification = employee.getClassification();
            if (paymentClassification instanceof CommissionedClassification commissionedClassification) {
                commissionedClassification.addSalesReceipt(new SalesReceipt(date, amount));
            } else {
                throw new Exception("Tried to add salesReceipt to non-commissioned employee");
            }
        } else {
            throw new Exception("No such employee");
        }
    }
}

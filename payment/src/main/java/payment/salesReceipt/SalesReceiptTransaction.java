package payment.salesReceipt;

import payment.Transaction;
import payment.PaymentDatabase;
import payment.classification.CommissionedClassification;
import payment.classification.PaymentClassification;
import payment.entity.Employee;
import payment.entity.SalesReceipt;

public class SalesReceiptTransaction implements Transaction {
    private final Long date;
    private final double amount;
    private final Integer empId;

    public SalesReceiptTransaction(long date, double amount, Integer empId) {
        this.date = date;
        this.amount = amount;
        this.empId = empId;
    }

    @Override
    public void execute() throws Exception {
        Employee employee = PaymentDatabase.getEmployee(empId);
        if (employee != null) {
            PaymentClassification paymentClassification = employee.getClassification();
            if (paymentClassification instanceof CommissionedClassification commissionedClassification) {
                commissionedClassification.addSalesReceipt(SalesReceipt.builder().date(date).amount(amount).build());
            } else {
                throw new Exception("Tried to add salesReceipt to non-commissioned employee");
            }
        } else {
            throw new Exception("No such employee");
        }
    }
}

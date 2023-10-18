package payment.classification;

import payment.entity.Paycheck;
import payment.entity.SalesReceipt;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
    private final double salary;
    private final double commissionRate;
    private Map<Date, SalesReceipt> salesReceiptMap;

    public CommissionedClassification(double salary, double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    public void addSalesReceipt(SalesReceipt salesReceipt) {
        salesReceiptMap.put(salesReceipt.getDate(), salesReceipt);
    }

    public double getSalary() {
        return salary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public SalesReceipt getSalesReceipt(Date date) {
        return salesReceiptMap.get(date);
    }

    @Override
    public double calculatePay(Paycheck pc) {
        return 0;
    }
}

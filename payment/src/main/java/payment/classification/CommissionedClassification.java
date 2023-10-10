package payment.classification;

import payment.entity.Paycheck;
import payment.entity.SalesReceipt;

import java.util.Collection;

public class CommissionedClassification implements PaymentClassification {
    private final double salary;
    private final double commissionRate;
    private Collection<SalesReceipt> salesReceipts;

    public CommissionedClassification(double salary, double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    public void addSalesReceipt(SalesReceipt salesReceipt) {
        salesReceipts.add(salesReceipt);
    }

    public double getSalary() {
        return salary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public SalesReceipt getSalesReceipt(long date) {
        return SalesReceipt.builder().build();
    }

    @Override
    public double calculatePay(Paycheck pc) {
        return 0;
    }
}

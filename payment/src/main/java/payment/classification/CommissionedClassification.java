package payment.classification;

import payment.entity.SalesReceipt;

public class CommissionedClassification extends PaymentClassification {
    private final double salary;
    private final double commissionRate;

    public CommissionedClassification(double salary, double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    public void addSalesReceipt(SalesReceipt salesReceipt) {

    }

    public double getSalary() {
        return salary;
    }

    public double getCommissionRate() {
        return commissionRate;
    }
}

package payment.PayrollImplementation;

import lombok.Getter;
import payment.PayrollDomain.PaymentClassification;
import payment.PayrollDomain.Paycheck;

import java.util.Date;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
    @Getter
    private final double salary;
    @Getter
    private final double commissionRate;
    private Map<Date, SalesReceipt> salesReceiptMap;

    public CommissionedClassification(double salary, double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }

    public void addSalesReceipt(SalesReceipt salesReceipt) {
        salesReceiptMap.put(salesReceipt.getDate(), salesReceipt);
    }

    public SalesReceipt getSalesReceipt(Date date) {
        return salesReceiptMap.get(date);
    }

    @Override
    public double calculatePay(Paycheck pc) {
        return 0;
    }
}

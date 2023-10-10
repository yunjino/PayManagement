package payment.classification;

import payment.entity.Paycheck;

public class SalariedClassification implements PaymentClassification {
    private final double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public double calculatePay(Paycheck pc) {
        return 0;
    }
}

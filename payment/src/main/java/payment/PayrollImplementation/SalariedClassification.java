package payment.PayrollImplementation;

import lombok.Getter;
import payment.PayrollDomain.PaymentClassification;
import payment.PayrollDomain.Paycheck;

@Getter
public class SalariedClassification implements PaymentClassification {
    private final double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    @Override
    public double calculatePay(Paycheck pc) {
        return 0;
    }
}

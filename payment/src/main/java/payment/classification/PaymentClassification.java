package payment.classification;

import payment.entity.Paycheck;

public interface PaymentClassification {
    double calculatePay(Paycheck pc);
}

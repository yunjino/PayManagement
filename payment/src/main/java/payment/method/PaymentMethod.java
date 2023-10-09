package payment.method;

import payment.entity.Paycheck;

public interface PaymentMethod {
    void pay(Paycheck paycheck);
}

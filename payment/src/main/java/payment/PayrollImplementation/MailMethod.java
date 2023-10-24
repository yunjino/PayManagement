package payment.PayrollImplementation;

import lombok.Getter;
import payment.PayrollDomain.Paycheck;
import payment.PayrollDomain.PaymentMethod;

@Getter
public class MailMethod implements PaymentMethod {
    private final String address;

    public MailMethod(String address) {
        this.address = address;
    }

    @Override
    public void pay(Paycheck paycheck) {

    }
}

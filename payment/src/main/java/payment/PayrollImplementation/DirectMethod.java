package payment.PayrollImplementation;

import lombok.Getter;
import payment.PayrollDomain.Paycheck;
import payment.PayrollDomain.PaymentMethod;

@Getter
public class DirectMethod implements PaymentMethod {
    private final String bank;
    private final String account;

    public DirectMethod(String bank, String account) {
        this.bank = bank;
        this.account = account;
    }

    @Override
    public void pay(Paycheck paycheck) {

    }
}

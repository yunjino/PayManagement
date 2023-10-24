package payment.PayrollImplementation;

import payment.PayrollDomain.Affiliation;
import payment.PayrollDomain.Paycheck;

public class NoAffiliation implements Affiliation {

    @Override
    public double calculateDeductions(Paycheck paycheck) {
        return 0;
    }
}

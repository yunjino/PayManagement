package payment.affiliation;

import payment.entity.Paycheck;
import payment.entity.ServiceCharge;

public class NoAffiliation implements Affiliation {

    @Override
    public double calculateDeductions(Paycheck paycheck) {
        return 0;
    }
}

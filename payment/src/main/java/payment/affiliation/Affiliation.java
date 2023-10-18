package payment.affiliation;

import payment.entity.Paycheck;
import payment.entity.ServiceCharge;

public interface Affiliation {
    double calculateDeductions(Paycheck paycheck);
}

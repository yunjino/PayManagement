package payment.affiliation;

import payment.entity.Paycheck;
import payment.entity.ServiceCharge;

public interface Affiliation {
    void addServiceChange(ServiceCharge serviceCharge);
    double calculateDeductions(Paycheck paycheck);
}

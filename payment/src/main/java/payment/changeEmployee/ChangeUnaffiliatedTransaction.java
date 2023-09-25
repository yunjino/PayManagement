package payment.changeEmployee;

import payment.entity.Employee;
import payment.serviceCharge.Affiliation;
import payment.serviceCharge.NoAffiliation;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
    public ChangeUnaffiliatedTransaction(Integer empId) {
        super(empId);
    }

    @Override
    void recordMemberShip(Employee e) {
        Affiliation affiliation = e.getAffiliation();
        // remove
    }

    @Override
    Affiliation getAffiliation() {
        return new NoAffiliation();
    }
}

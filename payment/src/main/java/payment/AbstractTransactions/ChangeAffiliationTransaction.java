package payment.AbstractTransactions;

import payment.PayrollDomain.Employee;
import payment.PayrollDomain.Affiliation;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
    public ChangeAffiliationTransaction(Integer empId) {
        super(empId);
    }

    @Override
    public void change(Employee e) {
        recordMemberShip(e);
        e.setAffiliation(getAffiliation());
    }

    protected abstract void recordMemberShip(Employee e);

    protected abstract Affiliation getAffiliation();
}

package payment.changeEmployee;

import payment.entity.Employee;
import payment.entity.UnionAffiliation;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
    public ChangeAffiliationTransaction(Integer empId) {
        super(empId);
    }

    @Override
    void change(Employee e) {
        recordMemberShip(e);
        e.setAffiliation(getAffiliation());
    }

    abstract void recordMemberShip(Employee e);

    abstract UnionAffiliation getAffiliation();
}

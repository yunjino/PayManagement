package payment.changeEmployee;

import payment.entity.Employee;
import payment.entity.UnionAffiliation;

public abstract class ChangeAffilationTransaction extends ChangeEmployeeTransaction {
    public ChangeAffilationTransaction(Integer empId) {
        super(empId);
    }

    @Override
    void change(Employee e) {
        recordMemberShip(e);
        e.setUnionAffiliation(getAffiliation());
    }

    abstract void recordMemberShip(Employee e);

    abstract UnionAffiliation getAffiliation();
}

package payment.changeEmployee;

import payment.entity.Employee;
import payment.entity.UnionAffiliation;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
    private final Integer memberId;
    private final double dues;

    public ChangeMemberTransaction(Integer empId, Integer memberId, double dues) {
        super(empId);
        this.memberId = memberId;
        this.dues = dues;
    }

    @Override
    void recordMemberShip(Employee e) {
        // 저장
    }

    @Override
    UnionAffiliation getAffiliation() {
        return new UnionAffiliation(memberId, dues);
    }
}

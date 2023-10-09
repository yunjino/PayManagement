package payment.changeEmployee;

import payment.PaymentDatabase;
import payment.entity.Employee;
import payment.affiliation.UnionAffiliation;
import payment.affiliation.Affiliation;

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
        PaymentDatabase.addUnionMember(memberId, e);
    }

    @Override
    Affiliation getAffiliation() {
        return new UnionAffiliation(dues, memberId);
    }
}

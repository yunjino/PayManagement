package payment.changeEmployee;

import payment.PaymentDatabase;
import payment.affiliation.UnionAffiliation;
import payment.entity.Employee;
import payment.affiliation.Affiliation;
import payment.affiliation.NoAffiliation;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
    public ChangeUnaffiliatedTransaction(Integer empId) {
        super(empId);
    }

    @Override
    void recordMemberShip(Employee e) {
        Affiliation affiliation = e.getAffiliation();
        if(affiliation instanceof UnionAffiliation) {
            UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
            int memberId = unionAffiliation.getMemberId();
            PaymentDatabase.removeUnionMember(memberId);
        }
    }

    @Override
    Affiliation getAffiliation() {
        return new NoAffiliation();
    }
}

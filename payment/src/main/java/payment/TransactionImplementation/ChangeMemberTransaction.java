package payment.TransactionImplementation;

import payment.AbstractTransactions.ChangeAffiliationTransaction;
import payment.PayrollDatabase.PaymentDatabase;
import payment.PayrollDomain.Employee;
import payment.PayrollImplementation.UnionAffiliation;
import payment.PayrollDomain.Affiliation;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
    private final Integer memberId;
    private final double dues;

    public ChangeMemberTransaction(Integer empId, Integer memberId, double dues) {
        super(empId);
        this.memberId = memberId;
        this.dues = dues;
    }

    @Override
    public void recordMemberShip(Employee e) {
        // 저장
        PaymentDatabase.addUnionMember(memberId, e);
    }

    @Override
    public Affiliation getAffiliation() {
        return new UnionAffiliation(dues, memberId);
    }
}

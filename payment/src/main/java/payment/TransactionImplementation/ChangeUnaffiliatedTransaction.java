package payment.TransactionImplementation;

import payment.AbstractTransactions.ChangeAffiliationTransaction;
import payment.PayrollDatabase.PaymentDatabase;
import payment.PayrollImplementation.UnionAffiliation;
import payment.PayrollDomain.Employee;
import payment.PayrollDomain.Affiliation;
import payment.PayrollImplementation.NoAffiliation;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {
    public ChangeUnaffiliatedTransaction(Integer empId) {
        super(empId);
    }

    @Override
    public void recordMemberShip(Employee e) {
        Affiliation affiliation = e.getAffiliation();
        if(affiliation instanceof UnionAffiliation) {
            UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
            int memberId = unionAffiliation.getMemberId();
            PaymentDatabase.removeUnionMember(memberId);
        }
    }

    @Override
    protected Affiliation getAffiliation() {
        return new NoAffiliation();
    }
}

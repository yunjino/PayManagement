package payment.TransactionImplementation;

import payment.AbstractTransactions.ChangeMethodTransaction;
import payment.PayrollImplementation.HoldMethod;
import payment.PayrollDomain.PaymentMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
    public ChangeHoldTransaction(Integer empId) {
        super(empId);
    }

    @Override
    public PaymentMethod getMethod() {
        return new HoldMethod();
    }
}

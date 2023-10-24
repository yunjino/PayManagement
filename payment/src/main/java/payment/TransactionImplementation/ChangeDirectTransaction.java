package payment.TransactionImplementation;

import payment.AbstractTransactions.ChangeMethodTransaction;
import payment.PayrollImplementation.DirectMethod;
import payment.PayrollDomain.PaymentMethod;

public class ChangeDirectTransaction extends ChangeMethodTransaction {
    private final String bank;
    private final String account;

    public ChangeDirectTransaction(Integer empId, String bank, String account) {
        super(empId);
        this.bank = bank;
        this.account = account;
    }

    @Override
    public PaymentMethod getMethod() {
        return new DirectMethod(bank, account);
    }
}

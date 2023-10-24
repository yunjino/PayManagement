package payment.TransactionImplementation;

import payment.AbstractTransactions.ChangeMethodTransaction;
import payment.PayrollImplementation.MailMethod;
import payment.PayrollDomain.PaymentMethod;

public class ChangeMailTransaction extends ChangeMethodTransaction {
    private final String address;

    public ChangeMailTransaction(Integer empId, String address) {
        super(empId);
        this.address = address;
    }

    @Override
    public PaymentMethod getMethod() {
        return new MailMethod(address);
    }
}

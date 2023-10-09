package payment.changeEmployee;

import payment.method.DirectMethod;
import payment.method.PaymentMethod;

public class ChangeDirectTransaction extends ChangeMethodTransaction {
    private final String bank;
    private final String account;

    public ChangeDirectTransaction(Integer empId, String bank, String account) {
        super(empId);
        this.bank = bank;
        this.account = account;
    }

    @Override
    PaymentMethod getMethod() {
        return new DirectMethod(bank, account);
    }
}

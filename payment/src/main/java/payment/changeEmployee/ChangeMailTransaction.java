package payment.changeEmployee;

import payment.method.MailMethod;
import payment.method.PaymentMethod;

public class ChangeMailTransaction extends ChangeMethodTransaction {
    private final String address;

    public ChangeMailTransaction(Integer empId, String address) {
        super(empId);
        this.address = address;
    }

    @Override
    PaymentMethod getMethod() {
        return new MailMethod(address);
    }
}

package payment.TransactionImplementation;

import payment.AbstractTransactions.ChangeEmployeeTransaction;
import payment.PayrollDomain.Employee;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
    private final String address;

    public ChangeAddressTransaction(Integer empId, String address) {
        super(empId);
        this.address = address;
    }

    @Override
    public void change(Employee e) {
        e.setAddress(address);
    }
}

package payment.AbstractTransactions;

import payment.PayrollDomain.Employee;
import payment.PayrollDomain.PaymentMethod;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {
    public ChangeMethodTransaction(Integer empId) {
        super(empId);
    }

    @Override
    public void change(Employee e) {
        e.setMethod(getMethod());
    }

    protected abstract PaymentMethod getMethod();
}

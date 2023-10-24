package payment.TransactionImplementation;

import payment.AbstractTransactions.ChangeEmployeeTransaction;
import payment.PayrollDomain.Employee;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private final String name;

    public ChangeNameTransaction(Integer empId, String name) {
        super(empId);
        this.name = name;
    }

    @Override
    public void change(Employee e) {
        e.setName(name);
    }
}

package payment.changeEmployee;

import payment.entity.Employee;
import payment.method.PaymentMethod;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {
    public ChangeMethodTransaction(Integer empId) {
        super(empId);
    }

    @Override
    void change(Employee e) {
        e.setMethod(getMethod());
    }

    abstract PaymentMethod getMethod();
}

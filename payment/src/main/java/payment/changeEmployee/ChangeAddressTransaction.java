package payment.changeEmployee;

import payment.entity.Employee;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
    private final String address;

    public ChangeAddressTransaction(Integer empId, String address) {
        super(empId);
        this.address = address;
    }

    @Override
    void change(Employee e) {
        e.setAddress(address);
    }
}

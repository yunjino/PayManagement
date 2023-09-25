package payment.changeEmployee;

import payment.entity.Employee;

public class changeAddressTransaction extends changeEmployeeTransaction {
    private final String address;

    public changeAddressTransaction(Integer empId, String address) {
        super(empId);
        this.address = address;
    }

    @Override
    void change(Employee e) {
        e.setAddress(address);
    }
}

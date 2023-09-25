package payment.changeEmployee;

import payment.entity.Employee;

public class changeNameTransaction extends changeEmployeeTransaction {
    private final String name;

    public changeNameTransaction(Integer empId, String name) {
        super(empId);
        this.name = name;
    }

    @Override
    public void change(Employee e) {
        e.setName(name);
    }
}

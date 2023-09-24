package payment.addEmployee;

public class AddSalariedEmployee extends AddEmployeeTransaction{
    private final double salary;

    protected AddSalariedEmployee(Integer empId, String name, String address, double salary) {
        super(empId, name, address);
        this.salary = salary;
    }
}

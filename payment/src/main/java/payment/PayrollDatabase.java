package payment;

import payment.entity.Employee;

import java.util.HashMap;
import java.util.Map;

public class PayrollDatabase {
    private static Map<Integer, Employee> itsEmployee = new HashMap<>();

    public PayrollDatabase() {
    }

    public static Employee getEmployee(int empId) {
        return itsEmployee.get(empId);
    }

    public static void addEmployee(int empId, Employee employee) {
        itsEmployee.put(empId, employee);
    }

    public static Employee deleteEmployee(int empId) {
        return itsEmployee.remove(empId);
    }
}

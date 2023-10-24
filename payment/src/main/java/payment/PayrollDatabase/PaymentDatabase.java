package payment.PayrollDatabase;

import payment.PayrollDomain.Employee;
import payment.PayrollDomain.Paycheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentDatabase {
    private static Map<Integer, Employee> itsEmployee = new HashMap<>();
    private static Map<Integer, Employee> itsUnion = new HashMap<>();
    private static Map<Integer, Paycheck> itsPaycheck = new HashMap<>();

    public static boolean isExist(int empId) {
        return itsEmployee.containsKey(empId);
    }

    public static Employee getEmployee(int empId) {
        return itsEmployee.get(empId);
    }

    public static Employee getUnion(int memberId) {
        return itsUnion.get(memberId);
    }

    public static Paycheck getPaycheck(int empId) {
        return itsPaycheck.get(empId);
    }

    public static void addEmployee(int empId, Employee employee) {
        itsEmployee.put(empId, employee);
    }

    public static void addUnionMember(int memberId, Employee employee) {
        itsUnion.put(memberId, employee);
    }

    public static void addPaycheck(int empId, Paycheck paycheck) {
        itsPaycheck.put(empId, paycheck);
    }

    public static void deleteEmployee(int empId) {
        itsEmployee.remove(empId);
    }

    public static void removeUnionMember(int memberId) {
        itsUnion.remove(memberId);
    }

    public static List<Integer> getAllEmployeeIds() {
        return new ArrayList<>(itsEmployee.keySet());
    }
}

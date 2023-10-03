import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import payment.PaymentDatabase;
import payment.addEmployee.AddCommissionedEmployee;
import payment.addEmployee.AddHourlyEmployee;
import payment.addEmployee.AddSalariedEmployee;
import payment.classification.CommissionedClassification;
import payment.classification.HourlyClassification;
import payment.classification.PaymentClassification;
import payment.classification.SalariedClassification;
import payment.entity.Employee;
import payment.method.HoldMethod;
import payment.method.PaymentMethod;
import payment.schedule.*;

public class PaymentDatabaseTest {

    @Test
    public void testAddSalariedEmployee() {
        int empId = 1;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        addSalariedEmployee.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);
        Assertions.assertEquals("Bob", employee.getName());

        PaymentClassification paymentClassification = employee.getClassification();
        SalariedClassification salariedClassification = (SalariedClassification) paymentClassification;
        Assertions.assertEquals(1000.00, salariedClassification.getSalary(), .001);

        PaymentSchedule paymentSchedule = employee.getSchedule();
        MonthlySchedule monthlySchedule = (MonthlySchedule) paymentSchedule;
        Assertions.assertEquals(paymentSchedule, monthlySchedule);

        PaymentMethod paymentMethod = employee.getMethod();
        HoldMethod holdMethod = (HoldMethod) paymentMethod;
        Assertions.assertEquals(paymentMethod, holdMethod);
    }

    @Test
    public void testAddHourlyEmployee() {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Alice", "House", 500.00);
        addHourlyEmployee.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);
        Assertions.assertEquals("Alice", employee.getName());

        PaymentClassification paymentClassification = employee.getClassification();
        HourlyClassification hourlyClassification = (HourlyClassification) paymentClassification;
        Assertions.assertEquals(500.00, hourlyClassification.getHourlyRate(), .001);

        PaymentSchedule paymentSchedule = employee.getSchedule();
        WeeklySchedule weeklySchedule = (WeeklySchedule) paymentSchedule;
        Assertions.assertEquals(paymentSchedule, weeklySchedule);

        PaymentMethod paymentMethod = employee.getMethod();
        HoldMethod holdMethod = (HoldMethod) paymentMethod;
        Assertions.assertEquals(paymentMethod, holdMethod);
    }

    @Test
    public void testAddCommissionedEmployee() {
        int empId = 3;
        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, "Jack", "dd", 1000.00, 500.00);
        addCommissionedEmployee.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);
        Assertions.assertEquals("Jack", employee.getName());

        PaymentClassification paymentClassification = employee.getClassification();
        CommissionedClassification commissionedClassification = (CommissionedClassification) paymentClassification;
        Assertions.assertEquals(1000.00, commissionedClassification.getSalary(), .001);
        Assertions.assertEquals(500.00, commissionedClassification.getCommissionRate(), .001);

        PaymentSchedule paymentSchedule = employee.getSchedule();
        BiweeklySchedule biweeklySchedule = (BiweeklySchedule) paymentSchedule;
        Assertions.assertEquals(paymentSchedule, biweeklySchedule);

        PaymentMethod paymentMethod = employee.getMethod();
        HoldMethod holdMethod = (HoldMethod) paymentMethod;
        Assertions.assertEquals(paymentMethod, holdMethod);


    }
}

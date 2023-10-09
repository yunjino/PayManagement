import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import payment.PaymentDatabase;
import payment.addEmployee.AddCommissionedEmployee;
import payment.addEmployee.AddHourlyEmployee;
import payment.addEmployee.AddSalariedEmployee;
import payment.changeEmployee.*;
import payment.classification.CommissionedClassification;
import payment.classification.HourlyClassification;
import payment.classification.PaymentClassification;
import payment.classification.SalariedClassification;
import payment.deleteEmployee.DeleteEmployeeTransaction;
import payment.entity.*;
import payment.method.DirectMethod;
import payment.method.HoldMethod;
import payment.method.MailMethod;
import payment.method.PaymentMethod;
import payment.salesReceipt.SalesReceiptTransaction;
import payment.schedule.*;
import payment.serviceCharge.ServiceChargeTransaction;
import payment.timeCard.TimeCardTransaction;

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

    @Test
    public void testDeleteEmployee() {
        int empId = 3;
        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, "Jack", "dd", 1000.00, 500.00);
        addCommissionedEmployee.execute();
        Employee employee = PaymentDatabase.getEmployee(empId);
        Assertions.assertEquals("Jack", employee.getName());

        DeleteEmployeeTransaction deleteEmployeeTransaction = new DeleteEmployeeTransaction(empId);
        deleteEmployeeTransaction.execute();

        Assertions.assertEquals(0, PaymentDatabase.isExist(empId));
    }

    @Test
    public void TestTimeCardTransaction() throws Exception {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        TimeCardTransaction timeCardTransaction = new TimeCardTransaction(empId, 20011031, 8.0);
        timeCardTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);

        PaymentClassification paymentClassification = employee.getClassification();
        HourlyClassification hourlyClassification = (HourlyClassification) paymentClassification;

        TimeCard timeCard = hourlyClassification.getTimeCard(20011031);
        Assertions.assertEquals(8.0, timeCard.getHours());
    }

    @Test
    public void TestSalesReceiptTransaction() throws Exception {
        int empId = 1;
        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, "Jack", "dd", 1000.00, 500.00);
        addCommissionedEmployee.execute();

        SalesReceiptTransaction salesReceiptTransaction = new SalesReceiptTransaction(20011031, 30000.0, empId);
        salesReceiptTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);
        PaymentClassification paymentClassification = employee.getClassification();
        CommissionedClassification commissionedClassification = (CommissionedClassification) paymentClassification;

        SalesReceipt salesReceipt = commissionedClassification.getSalesReceipt(20011031);
        Assertions.assertEquals(30000.0, salesReceipt.getAmount());
    }

    @Test
    public void TestAddServiceCharge() throws Exception {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "BIll", "Home", 15.25);
        addHourlyEmployee.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);
        UnionAffiliation unionAffiliation = new UnionAffiliation(12.5);
        employee.setAffiliation(unionAffiliation);

        int memberId = 86;
        PaymentDatabase.addUnionMember(memberId, employee);
        ServiceChargeTransaction serviceChargeTransaction = new ServiceChargeTransaction(memberId, 20011101, 12095);
        serviceChargeTransaction.execute();

        ServiceCharge serviceCharge = unionAffiliation.getServiceCharge(20011101);
        Assertions.assertEquals(12.95, serviceCharge.getAmount(), .001);
    }

    @Test
    public void TestChangeNameTransaction() {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        ChangeNameTransaction changeNameTransaction = new ChangeNameTransaction(empId, "Bob");
        changeNameTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);

        Assertions.assertEquals("Bob", employee.getName());
    }

    @Test
    public void TestChangeAddressTransaction() {
        int empId = 3;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        ChangeAddressTransaction changeAddressTransaction = new ChangeAddressTransaction(empId, "Home22");
        changeAddressTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);

        Assertions.assertEquals("Home22", employee.getAddress());
    }

    @Test
    public void TestChangeHourlyTransaction() {
        int empId = 3;
        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, "Lance", "Home", 2500, 3.2);
        addCommissionedEmployee.execute();

        ChangeHourlyTransaction changeHourlyTransaction = new ChangeHourlyTransaction(empId, 27.52);
        changeHourlyTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);

        PaymentClassification paymentClassification = employee.getClassification();
        Assertions.assertEquals(true, paymentClassification instanceof HourlyClassification);
        HourlyClassification hourlyClassification = (HourlyClassification) paymentClassification;

        Assertions.assertEquals(27.52, hourlyClassification.getHourlyRate(), .001);

        PaymentSchedule paymentSchedule = employee.getSchedule();
        Assertions.assertEquals(true, paymentSchedule instanceof WeeklySchedule);
    }

    @Test
    public void TestChangeSalariedTransaction() {
        int empId = 1;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        ChangeSalariedTransaction changeSalariedTransaction = new ChangeSalariedTransaction(empId, 1000.00);
        changeSalariedTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);

        PaymentClassification paymentClassification = employee.getClassification();
        Assertions.assertInstanceOf(SalariedClassification.class, paymentClassification);
        SalariedClassification salariedClassification = (SalariedClassification) paymentClassification;
        Assertions.assertEquals(1000.00, salariedClassification.getSalary(), .001);

        PaymentSchedule paymentSchedule = employee.getSchedule();
        Assertions.assertInstanceOf(MonthlySchedule.class, paymentSchedule);
    }

    @Test
    public void TestCommissionedTransaction() {
        int empId = 3;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        ChangeCommissionedTransaction changeCommissionedTransaction = new ChangeCommissionedTransaction(empId, 1000.00, 500.00);
        changeCommissionedTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);

        PaymentClassification paymentClassification = employee.getClassification();
        Assertions.assertInstanceOf(CommissionedClassification.class, paymentClassification);
        CommissionedClassification commissionedClassification = (CommissionedClassification) paymentClassification;
        Assertions.assertEquals(1000.00, commissionedClassification.getSalary(), .001);
        Assertions.assertEquals(500.00, commissionedClassification.getCommissionRate(), .001);

        PaymentSchedule paymentSchedule = employee.getSchedule();
        Assertions.assertInstanceOf(BiweeklySchedule.class, paymentSchedule);
    }

    @Test
    public void TestChangeDirectTransaction() {
        int empId = 3;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        ChangeDirectTransaction changeDirectTransaction = new ChangeDirectTransaction(empId, "bank", "Account");
        changeDirectTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);

        PaymentMethod paymentMethod = employee.getMethod();
        Assertions.assertInstanceOf(DirectMethod.class, paymentMethod);
        DirectMethod directMethod = (DirectMethod) paymentMethod;
        Assertions.assertEquals("bank", directMethod.getBank());
        Assertions.assertEquals("Account", directMethod.getAccount());
    }

    @Test
    public void TestChangeMailTransaction() {
        int empId = 3;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        ChangeMailTransaction changeMailTransaction = new ChangeMailTransaction(empId, "Home22");
        changeMailTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);

        PaymentMethod paymentMethod = employee.getMethod();
        Assertions.assertInstanceOf(MailMethod.class, paymentMethod);
        MailMethod mailMethod = (MailMethod) paymentMethod;
        Assertions.assertEquals("Home22", mailMethod.getAddress());
    }

    @Test
    public void TestChangeHoldTransaction() {
        int empId = 3;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        ChangeHoldTransaction changeHoldTransaction = new ChangeHoldTransaction(empId);
        changeHoldTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);

        PaymentMethod paymentMethod = employee.getMethod();
        Assertions.assertInstanceOf(HoldMethod.class, paymentMethod);
    }
}

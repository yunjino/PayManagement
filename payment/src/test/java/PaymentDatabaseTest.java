import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import payment.PaymentDatabase;
import payment.addEmployee.AddCommissionedEmployee;
import payment.addEmployee.AddHourlyEmployee;
import payment.addEmployee.AddSalariedEmployee;
import payment.affiliation.Affiliation;
import payment.affiliation.UnionAffiliation;
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
import payment.payday.PaydayTransaction;
import payment.salesReceipt.SalesReceiptTransaction;
import payment.schedule.*;
import payment.serviceCharge.ServiceChargeTransaction;
import payment.timeCard.TimeCardTransaction;

import java.util.Calendar;
import java.util.Date;

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

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 10, 31);

        TimeCardTransaction timeCardTransaction = new TimeCardTransaction(empId, payCalendar.getTime(), 8.0);
        timeCardTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);

        PaymentClassification paymentClassification = employee.getClassification();
        HourlyClassification hourlyClassification = (HourlyClassification) paymentClassification;

        TimeCard timeCard = hourlyClassification.getTimeCard(payCalendar.getTime());
        Assertions.assertEquals(8.0, timeCard.getHours());
    }

    @Test
    public void TestSalesReceiptTransaction() throws Exception {
        int empId = 1;
        AddCommissionedEmployee addCommissionedEmployee = new AddCommissionedEmployee(empId, "Jack", "dd", 1000.00, 500.00);
        addCommissionedEmployee.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 10, 31);

        SalesReceiptTransaction salesReceiptTransaction = new SalesReceiptTransaction(payCalendar.getTime(), 30000.0, empId);
        salesReceiptTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);
        PaymentClassification paymentClassification = employee.getClassification();
        CommissionedClassification commissionedClassification = (CommissionedClassification) paymentClassification;

        SalesReceipt salesReceipt = commissionedClassification.getSalesReceipt(payCalendar.getTime());
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

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 01);

        ServiceChargeTransaction serviceChargeTransaction = new ServiceChargeTransaction(memberId, payCalendar.getTime(), 12095);
        serviceChargeTransaction.execute();

        ServiceCharge serviceCharge = unionAffiliation.getServiceCharge(payCalendar.getTime());
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

    @Test
    public void TestChangeMemberTransaction() {
        int empId = 2;
        int memberId = 7734;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(empId, memberId, 99.42);
        changeMemberTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);
        Affiliation affiliation = employee.getAffiliation();
        Assertions.assertInstanceOf(UnionAffiliation.class, affiliation);
        UnionAffiliation unionAffiliation = (UnionAffiliation) affiliation;
        Assertions.assertEquals(99.42, unionAffiliation.getDues(), .001);

        Employee member = PaymentDatabase.getUnion(memberId);
        Assertions.assertEquals(employee, member);
    }

    @Test
    public void TestChangeUnaffiliatedTransaction() {
        int empId = 2;
        int memberId = 7734;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(empId, memberId, 99.42);
        changeMemberTransaction.execute();

        Employee employee = PaymentDatabase.getEmployee(empId);

        ChangeUnaffiliatedTransaction changeUnaffiliatedTransaction = new ChangeUnaffiliatedTransaction(empId);
        changeUnaffiliatedTransaction.execute();

        Assertions.assertEquals(employee, PaymentDatabase.getUnion(memberId));
    }

    @Test
    public void TestPaySingleSalariedEmployee() {
        int empId = 1;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        addSalariedEmployee.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 30);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payCalendar.getTime());
        paydayTransaction.execute();

        Paycheck payCheck = paydayTransaction.getPayCheck(empId);
        Assertions.assertEquals(payCalendar.getTime(), payCheck.getPayDate());
        Assertions.assertEquals(1000.00, payCheck.getCrossPay(), .001);
        Assertions.assertEquals("Hold", payCheck.getField().get("Disposition"));
        Assertions.assertEquals(0.0, payCheck.getDeductions(), .001);
        Assertions.assertEquals(1000.00, payCheck.getNetPay(), .001);
    }

    @Test
    public void TestPaySingleSalariedEmployeeOnWrongDate() {
        int empId = 1;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        addSalariedEmployee.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 29);
        PaydayTransaction paydayTransaction = new PaydayTransaction(payCalendar.getTime());
        paydayTransaction.execute();

        Paycheck payCheck = paydayTransaction.getPayCheck(empId);
        Assertions.assertNull(payCheck);
    }

    @Test
    public void TestPaySingleHourlyEmployeeNoTimeCards() {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 9); // 금요일
        PaydayTransaction paydayTransaction = new PaydayTransaction(payCalendar.getTime());
        paydayTransaction.execute();

        ValidateHourlyPaycheck(paydayTransaction, empId, payCalendar.getTime(), 0.0);
    }

    @Test
    public void ValidateHourlyPaycheck(PaydayTransaction paydayTransaction, int empId, Date payDate, double pay) {
        Paycheck payCheck = paydayTransaction.getPayCheck(empId);
        Assertions.assertEquals(payDate, payCheck.getPayDate());
        Assertions.assertEquals(pay, payCheck.getCrossPay(), .001);
        Assertions.assertEquals("Hold", payCheck.getField().get("Disposition"));
        Assertions.assertEquals(0.0, payCheck.getDeductions(), .001);
        Assertions.assertEquals(1000.00, payCheck.getNetPay(), .001);
    }

    @Test
    public void TestPaySingleHourlyEmployeeOneTimeCard() throws Exception {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 9); // 금요일
        Date payDate = payCalendar.getTime();

        TimeCardTransaction timeCardTransaction = new TimeCardTransaction(empId, payDate, 2.0);
        timeCardTransaction.execute();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        ValidateHourlyPaycheck(paydayTransaction, empId, payDate, 30.5);
    }

    @Test
    public void TestPaySingleHourlyEmployeeOvertimeOneTimeCard() throws Exception {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 9); // 금요일
        Date payDate = payCalendar.getTime();

        TimeCardTransaction timeCardTransaction = new TimeCardTransaction(empId, payDate, 9.0);
        timeCardTransaction.execute();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        ValidateHourlyPaycheck(paydayTransaction, empId, payDate, (8 + 1.5) * 15.25);
    }

    @Test
    public void TestPaySingleHourlyEmployeeOnWrongDate() throws Exception {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 8); // 목요일
        Date payDate = payCalendar.getTime();

        TimeCardTransaction timeCardTransaction = new TimeCardTransaction(empId, payDate, 9.0);
        timeCardTransaction.execute();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        Paycheck paycheck = paydayTransaction.getPayCheck(empId);
        Assertions.assertNull(paycheck);
    }

    @Test
    public void TestPaySingleHourlyEmployeeTwoTimeCards() throws Exception {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 9); // 금요일
        Date payDate = payCalendar.getTime();
        TimeCardTransaction timeCardTransaction1 = new TimeCardTransaction(empId, payDate, 2.0);
        timeCardTransaction1.execute();

        payCalendar.set(2001, 11, 8); // 목요일
        Date yesterdayPayDate = payCalendar.getTime();
        TimeCardTransaction timeCardTransaction2 = new TimeCardTransaction(empId, yesterdayPayDate, 5.0);
        timeCardTransaction2.execute();

        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        ValidateHourlyPaycheck(paydayTransaction, empId, payDate, 7 * 15.25);
    }

    @Test
    public void TestPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods() throws Exception {
        int empId = 2;
        AddHourlyEmployee addHourlyEmployee = new AddHourlyEmployee(empId, "Bill", "Home", 15.25);
        addHourlyEmployee.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 9); // 금요일
        Date payDate = payCalendar.getTime();
        TimeCardTransaction timeCardTransaction = new TimeCardTransaction(empId, payDate, 2.0);
        timeCardTransaction.execute();

        payCalendar.set(2001, 11, 2);
        Date dateInPreviousPayPeriod = payCalendar.getTime();
        TimeCardTransaction timeCardTransaction2 = new TimeCardTransaction(empId, dateInPreviousPayPeriod, 5.0);
        timeCardTransaction2.execute();

        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        ValidateHourlyPaycheck(paydayTransaction, empId, payDate, 2 * 15.25);
    }

    @Test
    public void TestSalariedUnionMemberDues() {
        int empId = 1;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        addSalariedEmployee.execute();

        int memberId = 7734;
        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(empId, memberId, 9.42);
        changeMemberTransaction.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 30);
        Date payDate = payCalendar.getTime();
        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        double consumerValue = 2.0;
        ValidatePayCheck(paydayTransaction, empId, payDate, 1000.0 - consumerValue);
    }

    public void ValidatePayCheck(PaydayTransaction paydayTransaction, int empId, Date payDate, double pay) {
        Paycheck payCheck = paydayTransaction.getPayCheck(empId);
        Assertions.assertEquals(payDate, payCheck.getPayDate());
        Assertions.assertEquals(pay, payCheck.getCrossPay(), .001);
        Assertions.assertEquals("Hold", payCheck.getField().get("Disposition"));
        Assertions.assertEquals(0.0, payCheck.getDeductions(), .001);
        Assertions.assertEquals(1000.00, payCheck.getNetPay(), .001);
    }

    @Test
    public void testPayHourlyUnionMemberServiceCharge() throws Exception {
        int empId = 1;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        addSalariedEmployee.execute();

        int memberId = 7734;
        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(empId, memberId, 9.42);
        changeMemberTransaction.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 9);
        Date payDate = payCalendar.getTime();

        ServiceChargeTransaction serviceChargeTransaction = new ServiceChargeTransaction(memberId, payDate, 19.42);
        serviceChargeTransaction.execute();

        TimeCardTransaction timeCardTransaction = new TimeCardTransaction(empId, payDate, 8.0);
        timeCardTransaction.execute();

        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        Paycheck paycheck = paydayTransaction.getPayCheck(empId);
        Assertions.assertEquals(payDate, paycheck.getPayPeriodEndDate());
        Assertions.assertEquals(8 * 15.24, paycheck.getCrossPay(), .001);
        Assertions.assertEquals("Hold", paycheck.getField().get("Disposition"));
        Assertions.assertEquals(9.42 + 19.42, paycheck.getDeductions(), .001);
        Assertions.assertEquals((8 * 15.24) - (9.42 + 19.42), paycheck.getNetPay(), .001);
    }

    @Test
    public void TestServiceChargesSpanningMultiplePayPeriods() throws Exception {
        int empId = 1;
        AddSalariedEmployee addSalariedEmployee = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00);
        addSalariedEmployee.execute();

        int memberId = 7734;
        ChangeMemberTransaction changeMemberTransaction = new ChangeMemberTransaction(empId, memberId, 9.42);
        changeMemberTransaction.execute();

        Calendar payCalendar = Calendar.getInstance();
        payCalendar.set(2001, 11, 2);
        Date earlyDate = payCalendar.getTime();
        payCalendar.set(2001, 11, 9);
        Date payDate = payCalendar.getTime();
        payCalendar.set(2001, 11, 16);
        Date lateDate = payCalendar.getTime();

        ServiceChargeTransaction serviceChargeTransaction = new ServiceChargeTransaction(memberId, payDate, 19.42);
        serviceChargeTransaction.execute();

        ServiceChargeTransaction serviceEarlyChargeTransaction = new ServiceChargeTransaction(memberId, earlyDate, 100.00);
        serviceEarlyChargeTransaction.execute();

        ServiceChargeTransaction serviceLateChargeTransaction = new ServiceChargeTransaction(memberId, lateDate, 200.00);
        serviceLateChargeTransaction.execute();

        TimeCardTransaction timeCardTransaction = new TimeCardTransaction(empId, payDate, 8.0);
        timeCardTransaction.execute();

        PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();

        Paycheck paycheck  = paydayTransaction.getPayCheck(empId);
        Assertions.assertEquals(payDate, paycheck.getPayPeriodEndDate());
        Assertions.assertEquals(8 * 15.24, paycheck.getCrossPay(), .001);
        Assertions.assertEquals("Hold", paycheck.getField().get("Disposition"));
        Assertions.assertEquals(9.42 + 19.42, paycheck.getDeductions(), .001);
        Assertions.assertEquals((8 * 15.24) - (9.42 + 19.42), paycheck.getNetPay(), .001);

    }
}

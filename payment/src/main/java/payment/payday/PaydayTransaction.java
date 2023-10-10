package payment.payday;

import payment.PaymentDatabase;
import payment.Transaction;
import payment.entity.Employee;
import payment.entity.Paycheck;

import java.util.Date;
import java.util.List;

public class PaydayTransaction implements Transaction {
    private final Date date;

    public PaydayTransaction(Date date) {
        this.date = date;
    }

    @Override
    public void execute() {
        List<Integer> empIds = PaymentDatabase.getAllEmployeeIds();
        for (Integer empId : empIds) {
            Employee employee = PaymentDatabase.getEmployee(empId);
            if (employee.isPayDay(date)) {
                Paycheck payCheck = new Paycheck(date);
                PaymentDatabase.addPaycheck(empId, payCheck);
                employee.payday(payCheck);
            }
        }
    }

    public Paycheck getPayCheck(int empId) {
        return PaymentDatabase.getPaycheck(empId);
    }
}

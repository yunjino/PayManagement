package payment.serviceCharge;

import org.springframework.beans.factory.annotation.Autowired;
import payment.Transaction;
import payment.PayrollDatabase;
import payment.entity.Employee;
import payment.entity.ServiceCharge;
import payment.entity.UnionAffiliation;

import java.util.Optional;

public class ServiceChargeTransaction implements Transaction {

    private final Long date;
    private final double charge;
    private final Integer memberId;

    public ServiceChargeTransaction(Long date, double charge, Integer memberId) {
        this.date = date;
        this.charge = charge;
        this.memberId = memberId;
    }

    @Override
    public void execute() throws Exception {
        Employee employee = Optional.ofNullable(PayrollDatabase.getEmployee(empId))
            .orElseThrow(() -> new Exception(("Not found employee")));

        UnionAffiliation affiliation = (UnionAffiliation) employee.getAffiliation();
        affiliation.addServiceChange(new ServiceCharge(date, charge));
    }
}

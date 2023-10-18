package payment.serviceCharge;

import payment.Transaction;
import payment.PaymentDatabase;
import payment.entity.Employee;
import payment.entity.ServiceCharge;
import payment.affiliation.UnionAffiliation;

import java.util.Date;
import java.util.Optional;

public class ServiceChargeTransaction implements Transaction {
    private final Integer memberId;
    private final Date date;
    private final double charge;

    public ServiceChargeTransaction(Integer memberId, Date date, double charge) {
        this.memberId = memberId;
        this.date = date;
        this.charge = charge;
    }

    @Override
    public void execute() throws Exception {
        Employee employee = Optional.ofNullable(PaymentDatabase.getEmployee(memberId))
            .orElseThrow(() -> new Exception(("Not found employee")));

        UnionAffiliation affiliation = (UnionAffiliation) employee.getAffiliation();
        affiliation.addServiceChange(ServiceCharge.builder().date(date).amount(charge).build());
    }
}

package payment.entity;

import payment.serviceCharge.Affiliation;

import javax.persistence.Entity;
import java.util.Collection;

@Entity
public class UnionAffiliation implements Affiliation {
    private final long date;
    private final double charge;
    private Collection<ServiceCharge> serviceCharges;

    public UnionAffiliation(long date, double charge) {
        this.date = date;
        this.charge = charge;
    }

    @Override
    public void addServiceChange(ServiceCharge serviceCharge) {
        serviceCharges.add(serviceCharge);
    }
}

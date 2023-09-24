package payment.entity;

import payment.serviceCharge.Affiliation;

import javax.persistence.Entity;

@Entity
public class UnionAffiliation implements Affiliation {
    private final long date;
    private final double charge;

    public UnionAffiliation(long date, double charge) {
        this.date = date;
        this.charge = charge;
    }

    public void addServiceCharge(ServiceCharge serviceCharge) {

    }
}

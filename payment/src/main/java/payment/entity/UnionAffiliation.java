package payment.entity;

import payment.Affiliation;

import javax.persistence.Entity;
import java.util.Collection;

@Entity
public class UnionAffiliation implements Affiliation {
    private Integer memberId;
    private double dues;
    private Collection<ServiceCharge> serviceCharges;

    public UnionAffiliation(Integer memberId, double dues) {
        this.memberId = memberId;
        this.dues = dues;
    }

    @Override
    public void addServiceChange(ServiceCharge serviceCharge) {
        serviceCharges.add(serviceCharge);
    }
}

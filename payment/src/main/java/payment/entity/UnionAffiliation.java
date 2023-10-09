package payment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import payment.Affiliation;

import java.util.Collection;

@Getter
@Setter
@Builder
public class UnionAffiliation implements Affiliation {
    private Integer memberId;
    private double dues;
    private Collection<ServiceCharge> serviceCharges;

    @Override
    public void addServiceChange(ServiceCharge serviceCharge) {
        serviceCharges.add(serviceCharge);
    }
}

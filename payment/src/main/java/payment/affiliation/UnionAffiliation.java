package payment.affiliation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import payment.entity.ServiceCharge;

import java.util.Collection;

@Getter
@Setter
@Builder
public class UnionAffiliation extends Affiliation {
    private double dues;
    private Integer memberId;
    private Collection<ServiceCharge> serviceCharges;

    public UnionAffiliation(double dues) {
        this.dues = dues;
    }

    public UnionAffiliation(double dues, Integer memberId) {
        this.dues = dues;
        this.memberId = memberId;
    }

    @Override
    public void addServiceChange(ServiceCharge serviceCharge) {
        serviceCharges.add(serviceCharge);
    }

    public ServiceCharge getServiceCharge(long date) {
        return ServiceCharge.builder().build();
    }
}

package payment.affiliation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import payment.entity.Paycheck;
import payment.entity.ServiceCharge;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Builder
public class UnionAffiliation implements Affiliation {
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

    private int numberOfFridaysInPayPeriod(Date startDate, Date endDate) {
        int count = 0;

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        while (startCalendar.compareTo(endCalendar) <= 0) {
            if (startCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                count++;
            }
            startCalendar.add(Calendar.DATE, 1);
        }
        return count;
    }

    @Override
    public double calculateDeductions(Paycheck paycheck) {
        int fridays = numberOfFridaysInPayPeriod(paycheck.getPayPeriodStartDate(), paycheck.getPayPeriodEndDate());
        return dues * fridays;
    }
}

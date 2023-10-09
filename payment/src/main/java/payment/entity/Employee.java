package payment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import payment.classification.PaymentClassification;
import payment.method.PaymentMethod;
import payment.schedule.PaymentSchedule;
import payment.affiliation.Affiliation;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class Employee {
    private Integer empId;
    private String name;
    private String address;
    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod method;
    private Affiliation affiliation;

    public Employee(Integer empId, String name, String address, PaymentClassification paymentClassification, PaymentSchedule paymentSchedule, PaymentMethod paymentMethod) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.classification = paymentClassification;
        this.schedule = paymentSchedule;
        this.method = paymentMethod;
    }
}

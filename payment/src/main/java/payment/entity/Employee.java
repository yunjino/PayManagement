package payment.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import payment.classification.PaymentClassification;
import payment.method.PaymentMethod;
import payment.schedule.PaymentSchedule;
import payment.Affiliation;

@Data
@Getter
@Setter
@Builder
public class Employee {
    private Integer empId;
    private String name;
    private String address;
    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod method;
    private Affiliation affiliation;
}

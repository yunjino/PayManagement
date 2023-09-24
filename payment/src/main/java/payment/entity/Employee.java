package payment.entity;

import payment.classification.PaymentClassification;
import payment.method.PaymentMethod;
import payment.schedule.PaymentSchedule;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    private Integer empId;
    private String name;
    private String address;

    public PaymentClassification getClassification() {
        return classification;
    }

    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod method;

    public Employee(Integer empId, String name, String address, PaymentClassification classification, PaymentSchedule schedule, PaymentMethod method) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.classification = classification;
        this.schedule = schedule;
        this.method = method;
    }


}

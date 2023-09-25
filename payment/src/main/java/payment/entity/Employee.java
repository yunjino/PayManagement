package payment.entity;

import payment.classification.PaymentClassification;
import payment.method.PaymentMethod;
import payment.schedule.PaymentSchedule;
import payment.Affiliation;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id
    private Integer empId;
    private String name;
    private String address;
    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod method;

    private Affiliation affiliation;

    public PaymentClassification getClassification() {
        return classification;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public Employee(Integer empId, String name, String address, PaymentClassification classification, PaymentSchedule schedule, PaymentMethod method) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.classification = classification;
        this.schedule = schedule;
        this.method = method;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setClassification(PaymentClassification paymentClassification) {
        this.classification = paymentClassification;
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }
}

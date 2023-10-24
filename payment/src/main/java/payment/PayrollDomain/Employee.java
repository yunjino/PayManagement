package payment.PayrollDomain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

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

    public boolean isPayDay(Date date) {
        return schedule.isPayDay(date);
    }

    public void payday(Paycheck payCheck) {
        double grossPay = classification.calculatePay(payCheck);
        double deductions = affiliation.calculateDeductions(payCheck);
        double netPay = grossPay - deductions;

        payCheck.setCrossPay(grossPay);
        payCheck.setDeductions(deductions);
        payCheck.setNetPay(netPay);

        method.pay(payCheck);
    }
}
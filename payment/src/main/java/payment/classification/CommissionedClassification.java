package payment.classification;

public class CommissionedClassification extends PaymentClassification {
    private final double salary;
    private final double commissionRate;

    public CommissionedClassification(double salary, double commissionRate) {
        this.salary = salary;
        this.commissionRate = commissionRate;
    }
}

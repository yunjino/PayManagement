package payment.classification;

public class SalariedClassification extends PaymentClassification {
    private final double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

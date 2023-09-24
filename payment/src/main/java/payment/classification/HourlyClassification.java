package payment.classification;

public class HourlyClassification extends PaymentClassification {
    private final double hourlyRate;

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}

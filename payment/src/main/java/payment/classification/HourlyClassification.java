package payment.classification;

import payment.entity.TimeCard;

public class HourlyClassification extends PaymentClassification {
    private final double hourlyRate;

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void addTimeCard(TimeCard timeCard) {

    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}

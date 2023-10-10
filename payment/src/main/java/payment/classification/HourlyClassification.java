package payment.classification;

import lombok.Getter;
import payment.entity.Paycheck;
import payment.entity.TimeCard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
public class HourlyClassification implements PaymentClassification {
    private final double hourlyRate;
    private Collection<TimeCard> timeCards = new ArrayList<>();

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void addTimeCard(TimeCard timeCard) {
        timeCards.add(timeCard);
    }

    public TimeCard getTimeCard(long date) {
        return timeCards.stream()
                .filter(timeCard -> timeCard.getDate() == date)
                .findAny()
                .get();
    }

    private double calculatePayForTimeCard(TimeCard timeCard) {
        double hours = timeCard.getHours();
        double overtime = Math.max(0.0, hours - 8.0);
        double straightTime = hours - overtime;
        return straightTime * hourlyRate + overtime * hourlyRate * 1.5;
    }

    @Override
    public double calculatePay(Paycheck pc) {
        double totalPay = 0;
        Date payPeriod = pc.getPayDate();
        for (TimeCard timeCard : timeCards) {
            if (isInPayPeriod(payPeriod, pc)) {
                totalPay += calculatePayForTimeCard(timeCard);
            }
        }
        return totalPay;
    }
}

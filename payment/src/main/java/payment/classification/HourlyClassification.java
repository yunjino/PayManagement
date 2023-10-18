package payment.classification;

import lombok.Getter;
import payment.entity.Paycheck;
import payment.entity.TimeCard;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class HourlyClassification implements PaymentClassification {
    private final double hourlyRate;
    private Map<Date, TimeCard> timeCardMap;

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void addTimeCard(TimeCard timeCard) {
        timeCardMap.put(timeCard.getDate(), timeCard);
    }

    public TimeCard getTimeCard(Date date) {
        return timeCardMap.get(date);
    }

    private double calculatePayForTimeCard(TimeCard timeCard) {
        double hours = timeCard.getHours();
        double overtime = Math.max(0.0, hours - 8.0);
        double straightTime = hours - overtime;
        return straightTime * hourlyRate + overtime * hourlyRate * 1.5;
    }

    @Override
    public double calculatePay(Paycheck pc) {
        AtomicReference<Double> totalPay = new AtomicReference<>((double) 0);
        Date payPeriod = pc.getPayDate();

        timeCardMap.keySet().forEach(date -> {
            TimeCard timeCard = timeCardMap.get(date);
            if(isInPayPeriod(payPeriod, pc)) {
                totalPay.updateAndGet(v -> v + calculatePayForTimeCard(timeCard));
            }
        });

        return totalPay.get();
    }
}

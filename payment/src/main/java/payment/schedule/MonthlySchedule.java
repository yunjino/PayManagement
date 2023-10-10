package payment.schedule;

import java.util.Calendar;
import java.util.Date;

public class MonthlySchedule implements PaymentSchedule {
    private boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.add(Calendar.DATE, 1);
        int monthByAdded1Day = calendar.get(Calendar.MONTH);
        return month != monthByAdded1Day;
    }

    @Override
    public boolean isPayDay(Date date) {
        return isLastDayOfMonth(date);
    }
}

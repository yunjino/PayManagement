package payment.schedule;

import java.util.Calendar;
import java.util.Date;

public class WeeklySchedule implements PaymentSchedule {
    private boolean isFriday(Date payDate) {
        Calendar payCalendar = Calendar.getInstance();
        payCalendar.setTime(payDate);
        int dayOfWeek = payCalendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.FRIDAY;
    }

    @Override
    public boolean isPayDay(Date date) {
        return isFriday(date);
    }
}

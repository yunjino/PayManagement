package payment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Builder
@Getter
@Setter
public class Paycheck {
    private Date payDate;
    private double crossPay;
    private Map<String, String> field;
    private double deductions;
    private double netPay;

    private Date payPeriodEndDate;
    private Date payPeriodStartDate;

    public Paycheck(Date payDate) {
        this.payDate = payDate;
    }
}

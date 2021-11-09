package TM;

import java.math.BigDecimal;

public class IncomeReportTM {

    private String classId;
    private String month;
    private BigDecimal profit;

    public IncomeReportTM() {
    }

    public IncomeReportTM(String classId, String month, BigDecimal profit) {
        this.classId = classId;
        this.month = month;
        this.profit = profit;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}

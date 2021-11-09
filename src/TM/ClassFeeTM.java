package TM;

import java.util.Date;

public class ClassFeeTM {
    private String FeeCode;
    private String stud_Id;
    private String relevent_Year;
    private String relevent_Month;
    private Date pay_Date;
    private String pay_Time;
    private Double fe_eCost;
    private String Status;

    public ClassFeeTM() {
    }

    public ClassFeeTM(String feeCode, String stud_Id, String relevent_Year, String relevent_Month, Date pay_Date, String pay_Time, Double fe_eCost, String status) {
        FeeCode = feeCode;
        this.stud_Id = stud_Id;
        this.relevent_Year = relevent_Year;
        this.relevent_Month = relevent_Month;
        this.pay_Date = pay_Date;
        this.pay_Time = pay_Time;
        this.fe_eCost = fe_eCost;
        Status = status;
    }

    public String getFeeCode() {
        return FeeCode;
    }

    public void setFeeCode(String feeCode) {
        FeeCode = feeCode;
    }

    public String getStud_Id() {
        return stud_Id;
    }

    public void setStud_Id(String stud_Id) {
        this.stud_Id = stud_Id;
    }

    public String getRelevent_Year() {
        return relevent_Year;
    }

    public void setRelevent_Year(String relevent_Year) {
        this.relevent_Year = relevent_Year;
    }

    public String getRelevent_Month() {
        return relevent_Month;
    }

    public void setRelevent_Month(String relevent_Month) {
        this.relevent_Month = relevent_Month;
    }

    public Date getPay_Date() {
        return pay_Date;
    }

    public void setPay_Date(Date pay_Date) {
        this.pay_Date = pay_Date;
    }

    public String getPay_Time() {
        return pay_Time;
    }

    public void setPay_Time(String pay_Time) {
        this.pay_Time = pay_Time;
    }

    public Double getFe_eCost() {
        return fe_eCost;
    }

    public void setFe_eCost(Double fe_eCost) {
        this.fe_eCost = fe_eCost;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
package Model;

import java.util.Date;

public class Payments {
    private String Pay_Code;
    private String Teach_Id;
    private String class_Id;
    private Double Pay_Cost;
    private String relevent_Year;
    private String relevent_Month;
    private Date pay_Date;
    private String pay_Time;
    private String Status;


    public Payments(String pay_Code, String teach_Id, String class_Id, Double pay_Cost, String relevent_Year, String relevent_Month, Date pay_Date, String pay_Time, String status) {
        Pay_Code = pay_Code;
        Teach_Id = teach_Id;
        this.class_Id = class_Id;
        Pay_Cost = pay_Cost;
        this.relevent_Year = relevent_Year;
        this.relevent_Month = relevent_Month;
        this.pay_Date = pay_Date;
        this.pay_Time = pay_Time;
        Status = status;
    }

    public Payments() {
    }

    public String getPay_Code() {
        return Pay_Code;
    }

    public void setPay_Code(String pay_Code) {
        Pay_Code = pay_Code;
    }

    public String getTeach_Id() {
        return Teach_Id;
    }

    public void setTeach_Id(String teach_Id) {
        Teach_Id = teach_Id;
    }

    public String getClass_Id() {
        return class_Id;
    }

    public void setClass_Id(String class_Id) {
        this.class_Id = class_Id;
    }

    public Double getPay_Cost() {
        return Pay_Cost;
    }

    public void setPay_Cost(Double pay_Cost) {
        Pay_Cost = pay_Cost;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

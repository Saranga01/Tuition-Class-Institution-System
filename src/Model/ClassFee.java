package Model;

import java.util.Date;

public class ClassFee {
  private String fee_Id;
  private String stud_Id;
  private String class_Id;
  private Double fe_eCost;
  private Date pay_Date;
  private String pay_Time;
  private String relevent_Year;
  private String relevent_Month;
  private String Status;
  private int Commiss;

    public ClassFee() {
    }

    public ClassFee(String fee_Id, String stud_Id, String class_Id, Double fe_eCost, Date pay_Date, String pay_Time, String relevent_Year, String relevent_Month, String status, int commiss) {
        this.fee_Id = fee_Id;
        this.stud_Id = stud_Id;
        this.class_Id = class_Id;
        this.fe_eCost = fe_eCost;
        this.pay_Date = pay_Date;
        this.pay_Time = pay_Time;
        this.relevent_Year = relevent_Year;
        this.relevent_Month = relevent_Month;
        Status = status;
        Commiss = commiss;
    }

    public String getFee_Id() {
        return fee_Id;
    }

    public void setFee_Id(String fee_Id) {
        this.fee_Id = fee_Id;
    }

    public String getStud_Id() {
        return stud_Id;
    }

    public void setStud_Id(String stud_Id) {
        this.stud_Id = stud_Id;
    }

    public String getClass_Id() {
        return class_Id;
    }

    public void setClass_Id(String class_Id) {
        this.class_Id = class_Id;
    }

    public Double getFe_eCost() {
        return fe_eCost;
    }

    public void setFe_eCost(Double fe_eCost) {
        this.fe_eCost = fe_eCost;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getCommiss() {
        return Commiss;
    }

    public void setCommiss(int commiss) {
        Commiss = commiss;
    }
}

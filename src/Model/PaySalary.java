package Model;

import java.util.Date;

public class PaySalary {
    private String salary_Id;
    private String employee_Id;
    private Double salary_Cost;
    private String r_year;
    private String r_Month;
    private Date date;
    private String time;
    private String Status;


    public PaySalary() {
    }

    public PaySalary(String salary_Id, String employee_Id, Double salary_Cost, String r_year, String r_Month, Date date, String time, String status) {
        this.salary_Id = salary_Id;
        this.employee_Id = employee_Id;
        this.salary_Cost = salary_Cost;
        this.r_year = r_year;
        this.r_Month = r_Month;
        this.date = date;
        this.time = time;
        Status = status;
    }

    public String getSalary_Id() {
        return salary_Id;
    }

    public void setSalary_Id(String salary_Id) {
        this.salary_Id = salary_Id;
    }

    public String getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(String employee_Id) {
        this.employee_Id = employee_Id;
    }

    public Double getSalary_Cost() {
        return salary_Cost;
    }

    public void setSalary_Cost(Double salary_Cost) {
        this.salary_Cost = salary_Cost;
    }

    public String getR_year() {
        return r_year;
    }

    public void setR_year(String r_year) {
        this.r_year = r_year;
    }

    public String getR_Month() {
        return r_Month;
    }

    public void setR_Month(String r_Month) {
        this.r_Month = r_Month;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

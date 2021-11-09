package Model;

import java.util.Date;

public class EmployeeReg {
    private String e_Id;
    private String e_Name;
    private String gender;
    private int mobile_Num;
    private String email;
    private String address;
    private String nic;
    private Date reg_Date;
    private String job_Title;

    public EmployeeReg() {
    }

    public EmployeeReg(String e_Id, String e_Name, String gender, int mobile_Num, String email, String address, String nic, Date reg_Date, String job_Title) {
        this.e_Id = e_Id;
        this.e_Name = e_Name;
        this.gender = gender;
        this.mobile_Num = mobile_Num;
        this.email = email;
        this.address = address;
        this.nic = nic;
        this.reg_Date = reg_Date;
        this.job_Title = job_Title;
    }

    public String getE_Id() {
        return e_Id;
    }

    public void setE_Id(String e_Id) {
        this.e_Id = e_Id;
    }

    public String getE_Name() {
        return e_Name;
    }

    public void setE_Name(String e_Name) {
        this.e_Name = e_Name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMobile_Num() {
        return mobile_Num;
    }

    public void setMobile_Num(int mobile_Num) {
        this.mobile_Num = mobile_Num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public Date getReg_Date() {
        return reg_Date;
    }

    public void setReg_Date(Date reg_Date) {
        this.reg_Date = reg_Date;
    }

    public String getJob_Title() {
        return job_Title;
    }

    public void setJob_Title(String job_Title) {
        this.job_Title = job_Title;
    }


}

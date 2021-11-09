package Model;

import java.util.Date;

public class StudentReg {
    private String stud_Id;
    private String stud_Name;
    private int Mobile_Num;
    private String Email;
    private String Birthday;
    private String Address;
    private String Gender;
    private String Trustee_Name;
    private String Nic;
    private Date Reg_Date;
    private String Class_id;

    public StudentReg(String stud_Id, String stud_Name, int mobile_Num, String email, String birthday, String address, String gender, String trustee_Name, String nic, Date reg_Date, String class_id) {
        this.stud_Id = stud_Id;
        this.stud_Name = stud_Name;
        Mobile_Num = mobile_Num;
        Email = email;
        Birthday = birthday;
        Address = address;
        Gender = gender;
        Trustee_Name = trustee_Name;
        Nic = nic;
        Reg_Date = reg_Date;
        Class_id = class_id;
    }

    public StudentReg() {
    }

    public String getStud_Id() {
        return stud_Id;
    }

    public void setStud_Id(String stud_Id) {
        this.stud_Id = stud_Id;
    }

    public String getStud_Name() {
        return stud_Name;
    }

    public void setStud_Name(String stud_Name) {
        this.stud_Name = stud_Name;
    }

    public int getMobile_Num() {
        return Mobile_Num;
    }

    public void setMobile_Num(int mobile_Num) {
        Mobile_Num = mobile_Num;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getTrustee_Name() {
        return Trustee_Name;
    }

    public void setTrustee_Name(String trustee_Name) {
        Trustee_Name = trustee_Name;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public Date getReg_Date() {
        return Reg_Date;
    }

    public void setReg_Date(Date reg_Date) {
        Reg_Date = reg_Date;
    }

    public String getClass_id() {
        return Class_id;
    }

    public void setClass_id(String class_id) {
        Class_id = class_id;
    }
}
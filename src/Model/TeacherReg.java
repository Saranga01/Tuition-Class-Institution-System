package Model;

import java.time.LocalDate;
import java.util.Date;

public class TeacherReg {
    private String TeacherID;
    private String TeacherName;
    private int TeacherMobile;
    private String TeacherEmail;
    private String TeacherSchoolName;
    private String TeacherNic;
    private String TeacherAddress;
    private String TeacherBirthday;
    private Date regDate;

    public TeacherReg(String teacherID, String teacherName, int teacherMobile, String teacherEmail, String teacherSchoolName, String teacherNic, String teacherAddress, String teacherBirthday, Date regDate) {
        TeacherID = teacherID;
        TeacherName = teacherName;
        TeacherMobile = teacherMobile;
        TeacherEmail = teacherEmail;
        TeacherSchoolName = teacherSchoolName;
        TeacherNic = teacherNic;
        TeacherAddress = teacherAddress;
        TeacherBirthday = teacherBirthday;
        this.regDate = regDate;
    }

    public TeacherReg() {
    }

    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String teacherID) {
        TeacherID = teacherID;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public int getTeacherMobile() {
        return TeacherMobile;
    }

    public void setTeacherMobile(int teacherMobile) {
        TeacherMobile = teacherMobile;
    }

    public String getTeacherEmail() {
        return TeacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        TeacherEmail = teacherEmail;
    }

    public String getTeacherSchoolName() {
        return TeacherSchoolName;
    }

    public void setTeacherSchoolName(String teacherSchoolName) {
        TeacherSchoolName = teacherSchoolName;
    }

    public String getTeacherNic() {
        return TeacherNic;
    }

    public void setTeacherNic(String teacherNic) {
        TeacherNic = teacherNic;
    }

    public String getTeacherAddress() {
        return TeacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        TeacherAddress = teacherAddress;
    }

    public String getTeacherBirthday() {
        return TeacherBirthday;
    }

    public void setTeacherBirthday(String  teacherBirthday) {
        TeacherBirthday = teacherBirthday;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}

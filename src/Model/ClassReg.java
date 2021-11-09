package Model;

import java.util.ArrayList;
import java.util.Date;

public class ClassReg {
    private String classId;
    private String teacherId;
    private String subjectName;
    private Double classFee;
    private String grade;
    private String title;
    private String classType;
    private String shortDesc;
    private Date regDate;
    private ArrayList schedule;

    public ClassReg(String classId, String teacherId, String subjectName, Double classFee, String grade, String title, String classType, String shortDesc, Date regDate, ArrayList schedule) {
        this.classId = classId;
        this.teacherId = teacherId;
        this.subjectName = subjectName;
        this.classFee = classFee;
        this.grade = grade;
        this.title = title;
        this.classType = classType;
        this.shortDesc = shortDesc;
        this.regDate = regDate;
        this.schedule = schedule;
    }

    public ClassReg() {
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Double getClassFee() {
        return classFee;
    }

    public void setClassFee(Double classFee) {
        this.classFee = classFee;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public ArrayList getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList schedule) {
        this.schedule = schedule;
    }
}
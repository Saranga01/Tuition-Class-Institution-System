package TM;

import java.sql.Date;
import java.time.LocalDate;

public class ScheduleTM {
    private String classId;
    private String scheduleType;
    private String date;
    private String day;
    private String time;

    public ScheduleTM() {
    }

    public ScheduleTM(String classId, String scheduleType, String date, String day, String time) {
        this.classId = classId;
        this.scheduleType = scheduleType;
        this.date = date;
        this.day = day;
        this.time = time;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String  date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

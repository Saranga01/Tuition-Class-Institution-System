package TM;

public class DayScheduleTM {
    private String day;
    private String Time;

    public DayScheduleTM(String day, String time) {
        this.day = day;
        Time = time;
    }

    public DayScheduleTM() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}

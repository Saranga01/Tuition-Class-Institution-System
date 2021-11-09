package Model;

public class GetDT {
    private String Date;
    private String Day;
    private String Time;

    public GetDT(String date, String day, String time) {
        Date = date;
        Day = day;
        Time = time;
    }

    public GetDT() {
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}

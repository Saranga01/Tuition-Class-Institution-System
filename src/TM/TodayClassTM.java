package TM;

public class TodayClassTM {
    private String ClassId;
    private String Time;

    public TodayClassTM() {
    }

    public TodayClassTM(String classId, String time) {
        ClassId = classId;
        Time = time;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        ClassId = classId;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}

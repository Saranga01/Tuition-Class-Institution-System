package TM;

public class ClassHomeTM {

    private String ClassId;
    private String TeacherId;
    private String Time;

    public ClassHomeTM() {
    }

    public ClassHomeTM(String classId, String teacherId, String time) {
        ClassId = classId;
        TeacherId = teacherId;
        Time = time;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        ClassId = classId;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        TeacherId = teacherId;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}


package lk.ehand.healthservice.domain;

public class DoctorAppoinmentSession {

    private String date;
    private String day;
    private String sessionStartTime;
    private int nextAppoinmentNo;

    public DoctorAppoinmentSession(){}

    public DoctorAppoinmentSession(String date, String day, String sessionStartTime, int nextAppoinmentNo) {
        this.date = date;
        this.day = day;
        this.sessionStartTime = sessionStartTime;
        this.nextAppoinmentNo = nextAppoinmentNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(String sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public int getNextAppoinmentNo() {
        return nextAppoinmentNo;
    }

    public void setNextAppoinmentNo(int nextAppoinmentNo) {
        this.nextAppoinmentNo = nextAppoinmentNo;
    }
}

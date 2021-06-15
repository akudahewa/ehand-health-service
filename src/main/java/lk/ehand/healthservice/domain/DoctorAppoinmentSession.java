package lk.ehand.healthservice.domain;

public class DoctorAppoinmentSession {

    private Long id;
    private String date;
    private String day;
    private String sessionStartTime;
    private String sessionEndTime;
    private int nextAppoinmentNo;
    private int maxCount;
    private String status;

    public DoctorAppoinmentSession(){}

    public DoctorAppoinmentSession(String date, String day, String sessionStartTime,
                                   int nextAppoinmentNo,String status,int maxCount
            ) {
        this.date = date;
        this.day = day;
        this.sessionStartTime = sessionStartTime;
        this.nextAppoinmentNo = nextAppoinmentNo;
        this.status =status;
        this.maxCount = maxCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(String sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.zerotoproduction.bucketlist.dto;

import java.io.Serializable;

public class DoctorScheduleGridDTO implements Serializable {

    private String dayOfWeek;
    private String sessionStart;
    private String sessionEnd;
    private String status;
    private int maxCount;

    public DoctorScheduleGridDTO(String dayOfWeek, String sessionStart, String sessionEnd, String status,int maxCount) {
        this.dayOfWeek = dayOfWeek;
        this.sessionStart = sessionStart;
        this.sessionEnd = sessionEnd;
        this.status = status;
        this.maxCount = maxCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(String sessionStart) {
        this.sessionStart = sessionStart;
    }

    public String getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(String sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

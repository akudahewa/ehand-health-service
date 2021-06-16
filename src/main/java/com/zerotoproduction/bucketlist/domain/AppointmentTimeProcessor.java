package com.zerotoproduction.bucketlist.domain;

import java.util.List;


public class AppointmentTimeProcessor {

    private Long doctorScheduleGridId;
    private String dayOfWeek;
    private String sessionStart;
    private String sessionEnd;
    private String status;
    private int maxCount;
    private List<DoctorScheduleGridAlteration> absentList;
    private List<DoctorScheduleGridAlteration> amendList;


    public AppointmentTimeProcessor(){}

    public Long getDoctorScheduleGridId() {
        return doctorScheduleGridId;
    }

    public void setDoctorScheduleGridId(Long doctorScheduleGridId) {
        this.doctorScheduleGridId = doctorScheduleGridId;
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

    public List<DoctorScheduleGridAlteration> getAbsentList() {
        return absentList;
    }

    public void setAbsentList(List<DoctorScheduleGridAlteration> absentList) {
        this.absentList = absentList;
    }

    public List<DoctorScheduleGridAlteration> getAmendList() {
        return amendList;
    }

    public void setAmendList(List<DoctorScheduleGridAlteration> amendList) {
        this.amendList = amendList;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
}

package com.zerotoproduction.bucketlist.dto;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class DoctorDispensaryDTO implements Serializable {

    private String doctor;
    private String dispensary;
    private String status;

    public DoctorDispensaryDTO(String doctor, String dispensary, String status) {
        this.doctor = doctor;
        this.dispensary = dispensary;
        this.status = status;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDispensary() {
        return dispensary;
    }

    public void setDispensary(String dispensary) {
        this.dispensary = dispensary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

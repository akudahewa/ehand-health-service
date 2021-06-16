package com.zerotoproduction.bucketlist.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

public class DoctorDispensaryId implements Serializable {

    Long doctor;

    Long dispensary;

    public DoctorDispensaryId(Long doctor, Long dispensary) {
        this.doctor = doctor;
        this.dispensary = dispensary;
    }

    public Long getDoctor() {
        return doctor;
    }

    public void setDoctor(Long doctor) {
        this.doctor = doctor;
    }

    public Long getDispensary() {
        return dispensary;
    }

    public void setDispensary(Long dispensary) {
        this.dispensary = dispensary;
    }
}

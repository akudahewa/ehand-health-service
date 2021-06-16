package com.zerotoproduction.bucketlist.domain;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refNumber;
    private String bookedDate;
    private int status;
    private String mobileNo;

    @ManyToOne
    @JoinColumn(name = "doctor_dispensary_grid_id",referencedColumnName = "id")
    private DoctorScheduleGrid doctorScheduleGrid;


    public Transaction(){}

    public Transaction(String refNumber, String bookedDate, int status, String mobileNo,
                       DoctorScheduleGrid doctorScheduleGrid) {
        this.refNumber = refNumber;
        this.bookedDate = bookedDate;
        this.status = status;
        this.mobileNo = mobileNo;
        this.doctorScheduleGrid = doctorScheduleGrid;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public DoctorScheduleGrid getDoctorScheduleGrid() {
        return doctorScheduleGrid;
    }

    public void setDoctorScheduleGrid(DoctorScheduleGrid doctorScheduleGrid) {
        this.doctorScheduleGrid = doctorScheduleGrid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package lk.ehand.healthservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "doctor_schedule_grid_alteration")
public class DoctorScheduleGridAlteration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "doctor_schedule_grid_id",referencedColumnName = "id")
    private DoctorScheduleGrid doctorScheduleGrid;

    private String date;
    private String sessionStartTime;
    private String status;
    private int maxCount;

    public DoctorScheduleGridAlteration(){}

    public DoctorScheduleGridAlteration(DoctorScheduleGrid doctorScheduleGrid, String date,String sessionStartTime,
                                        String status,int maxCount) {
        this.doctorScheduleGrid = doctorScheduleGrid;
        this.date = date;
        this.sessionStartTime = sessionStartTime;
        this.maxCount = maxCount;
        this.status = status;
    }

    public DoctorScheduleGrid getDoctorScheduleGrid() {
        return doctorScheduleGrid;
    }

    public void setDoctorScheduleGrid(DoctorScheduleGrid doctorScheduleGrid) {
        this.doctorScheduleGrid = doctorScheduleGrid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(String sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
}

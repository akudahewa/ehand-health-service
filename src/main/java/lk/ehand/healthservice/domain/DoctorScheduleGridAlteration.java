package lk.ehand.healthservice.domain;

import javax.persistence.*;

@Entity
@Table(name = "doctor_schedule_grid_alteration")
public class DoctorScheduleGridAlteration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "doctor_schedule_grid_id",referencedColumnName = "id")
    DoctorScheduleGrid doctorScheduleGrid;

    String date;
    String status;

    public DoctorScheduleGridAlteration(){}

    public DoctorScheduleGridAlteration(DoctorScheduleGrid doctorScheduleGrid, String date, String status) {
        this.doctorScheduleGrid = doctorScheduleGrid;
        this.date = date;
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
}

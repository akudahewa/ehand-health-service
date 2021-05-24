package lk.ehand.healthservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "doctor_dispensary")
public class DoctorDispensary implements Serializable {

    @Id
    @Column(name = "doctor_dispensary_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id",referencedColumnName = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "dispensary_id", referencedColumnName = "dispensary_id")
    private Dispensary dispensary;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "doctorDispensary",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<DoctorScheduleGrid> doctorScheduleGrids;

    public DoctorDispensary(){}

    public DoctorDispensary(Doctor doctor, Dispensary dispensary, List<DoctorScheduleGrid> doctorScheduleGrids,
                            String status) {
        this.doctor = doctor;
        this.dispensary = dispensary;
        this.doctorScheduleGrids = doctorScheduleGrids;
        this.status =status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Dispensary getDispensary() {
        return dispensary;
    }

    public void setDispensary(Dispensary dispensary) {
        this.dispensary = dispensary;
    }

    public List<DoctorScheduleGrid> getDoctorScheduleGrids() {
        return doctorScheduleGrids;
    }

    public void setDoctorScheduleGrids(List<DoctorScheduleGrid> doctorScheduleGrids) {
        this.doctorScheduleGrids = doctorScheduleGrids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

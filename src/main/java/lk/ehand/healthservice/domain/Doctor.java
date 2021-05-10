package lk.ehand.healthservice.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    Long doctorId;

    @Column(name = "name")
    String name;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<DoctorDispensary> doctorDispensaries;

    public Doctor(){}

    public Doctor(String name, List<DoctorDispensary> doctorDispensaries) {
        this.name = name;
        this.doctorDispensaries = doctorDispensaries;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DoctorDispensary> getDoctorDispensaries() {
        return doctorDispensaries;
    }

    public void setDoctorDispensaries(List<DoctorDispensary> doctorDispensaries) {
        this.doctorDispensaries = doctorDispensaries;
    }
}

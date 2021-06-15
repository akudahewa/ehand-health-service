package lk.ehand.healthservice.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
@JsonIgnoreProperties
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    Long doctorId;

    @Column(name = "title")
    String title;

    @Column(name = "name")
    String name;

    @Column(name = "speciality")
    String speciality;

    @Column(name = "short_description")
    String shortDescription;

    @Column(name = "description")
    String description;

    @Column(name = "image")
    String image;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<DoctorDispensary> doctorDispensaries;

    public Doctor(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

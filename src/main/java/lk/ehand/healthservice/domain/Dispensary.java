package lk.ehand.healthservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dispensary")
public class Dispensary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dispensary_id")
    private Long dispensaryId;

    @Column(name = "name")
    private String name;

    @Column(name="address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id",referencedColumnName = "city_id")
    private City city;

    @JsonIgnore
    @OneToMany(mappedBy = "dispensary", cascade = CascadeType.ALL)
    private List<DoctorDispensary> doctorDispensaries;

    public Dispensary(){}

    public Dispensary(String name, City city, List<DoctorDispensary> doctorDispensaries) {
        this.name = name;
        this.city = city;
        this.doctorDispensaries = doctorDispensaries;
    }

    public Long getDispensaryId() {
        return dispensaryId;
    }

    public void setDispensaryId(Long dispensaryId) {
        this.dispensaryId = dispensaryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<DoctorDispensary> getDoctorDispensaries() {
        return doctorDispensaries;
    }

    public void setDoctorDispensaries(List<DoctorDispensary> doctorDispensaries) {
        this.doctorDispensaries = doctorDispensaries;
    }
}

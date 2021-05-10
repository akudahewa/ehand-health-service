package lk.ehand.healthservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class City  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id")
    Long cityId;

    @Column(name="name")
    String name;

    @ManyToOne
    @JoinColumn(name = "district_id",referencedColumnName = "district_id")
    private District district;

    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Dispensary> dispensaries;

    public City(){}

    public City(String name, District district, List<Dispensary> dispensaries) {
        this.name = name;
        this.district = district;
        this.dispensaries = dispensaries;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Dispensary> getDispensaries() {
        return dispensaries;
    }

    public void setDispensaries(List<Dispensary> dispensaries) {
        this.dispensaries = dispensaries;
    }
}

package lk.ehand.healthservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "distrcit")
@JsonIgnoreProperties
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Long id;

    @NotBlank(message = "District name can't be blank")
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "district",cascade = CascadeType.ALL)
    private List<City> cities;

    public District(){}

    public District(String name ){
        this.name = name;
    }
    public District(String name, List<City> cities) {
        this.id = id;
        this.name = name;
        this.cities = cities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

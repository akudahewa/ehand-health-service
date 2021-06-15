package lk.ehand.healthservice.controller;

import lk.ehand.healthservice.domain.City;
import lk.ehand.healthservice.domain.District;
import lk.ehand.healthservice.domain.Doctor;
import lk.ehand.healthservice.exception.InternalServerException;
import lk.ehand.healthservice.repository.ICityRepository;
import lk.ehand.healthservice.repository.IDispensaryRepository;
import lk.ehand.healthservice.repository.IDistrictRepository;
import lk.ehand.healthservice.exception.ResourceNotFoundException;
import lk.ehand.healthservice.repository.IDoctorRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
@Data
public class CityController {

    private ICityRepository cityRepository;
    private IDistrictRepository districtRepository;
    private IDispensaryRepository dispensaryRepository;

    public CityController(ICityRepository cityRepository,IDispensaryRepository dispensaryRepository,
                          IDistrictRepository districtRepository){
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.dispensaryRepository = dispensaryRepository;
    }

    /**
     * return all cities for given districtId
     * @param districtId
     * @return list of cities
     */
    @GetMapping(value = "/district/{id}/city")
    public ResponseEntity<List<City>> getCities(@PathVariable String id){
        log.info("GET - Get all cities for districtId :{}",id);

        return ResponseEntity.ok().
                body(cityRepository.findCitiesByDistrictId(Long.parseLong(id)));
    }

    /**
     * save city for given city
     * @param city
     * @param districtId
     * @return saved city object
     */
    @PostMapping(value = "/district/{id}/city",consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public City saveCity(@RequestBody City city, @PathVariable("id") String districtId) {
        log.info("POST - create city ");
        return districtRepository.findById(Long.parseLong(districtId)).map(district -> {
            city.setDistrict(district);
            return cityRepository.save(city);
        }).orElseThrow(() -> new InternalServerException("City can't be saved"));

    }

    @PutMapping(value = "/city/{cityId}/doctor/{doctorId}")
    @ResponseStatus(HttpStatus.OK)
    public void saveCityDoctor(@PathVariable String cityId,@PathVariable String doctorId){
//        City city = cityRepository.findById(Long.parseLong(cityId))
//                .orElseThrow(()-> new ResourceNotFoundException("city not found"));
//        Doctor doctor = doctorRepository.findById(Long.parseLong(doctorId))
//                .orElseThrow(()->new ResourceNotFoundException("doctor not found"));
//        city.getDoctors().add(doctor);
          cityRepository.updateDoctorInCity(Long.parseLong(doctorId),
                Long.parseLong(cityId));
    }


}

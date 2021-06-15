package lk.ehand.healthservice.controller;

import lk.ehand.healthservice.domain.City;
import lk.ehand.healthservice.domain.Dispensary;
import lk.ehand.healthservice.exception.InternalServerException;
import lk.ehand.healthservice.exception.ResourceNotFoundException;
import lk.ehand.healthservice.repository.ICityRepository;
import lk.ehand.healthservice.repository.IDispensaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
@RequiredArgsConstructor
public class DispensaryController {

    private final ICityRepository cityRepository;
    private  final IDispensaryRepository dispensaryRepository;

    /**
     * create dispensary
     * @param dispensary
     * @param cityId
     * @return created dispensary object
     */
    @PostMapping(value = "/city/{id}/dispensary")
    public Dispensary saveDispensary(@RequestBody Dispensary dispensary, @PathVariable("id") String cityId){
        log.info("POST - save city :{}",dispensary);
        return cityRepository.findById(Long.parseLong(cityId)).map(city->{
            dispensary.setCity(city);
            return dispensaryRepository.save(dispensary);
        }).orElseThrow(()-> new ResourceNotFoundException("Dispensary cant save"));
    }

    /**
     * Get all dispensaries by cityId
     * @param id
     * @return List of dispensary by cityId
     */
    @GetMapping(value = "/city/{id}/dispensary")
    public ResponseEntity<List<Dispensary>> getDispensary(@PathVariable String id){
        log.info("GET : Get dispensary for given city :{}",id);
        return  ResponseEntity.ok()
                .body(dispensaryRepository.findDispensariesByCityId(Long.parseLong(id)));

    }
}

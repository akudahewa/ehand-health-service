package lk.ehand.healthservice.controller;

import lk.ehand.healthservice.domain.City;
import lk.ehand.healthservice.domain.Dispensary;
import lk.ehand.healthservice.exception.InternalServerException;
import lk.ehand.healthservice.exception.ResourceNotFoundException;
import lk.ehand.healthservice.repository.ICityRepository;
import lk.ehand.healthservice.repository.IDispensaryRepository;
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
public class DispensaryController {

    @Autowired
    ICityRepository cityRepository;
    @Autowired
    IDispensaryRepository dispensaryRepository;

    @PostMapping(value = "/city/{id}/dispensary")
    public ResponseEntity<Dispensary> saveDispensary(@RequestBody Dispensary dispensary, @PathVariable("id") String cityId){
        log.info("POST - save city :{}",dispensary);
        City city= cityRepository.findById(Long.parseLong(cityId))
                .orElseThrow(()-> new ResourceNotFoundException("City not found"));
        dispensary.setCity(city);
        return new ResponseEntity<>(dispensaryRepository.save(dispensary), HttpStatus.CREATED);
    }

    @GetMapping(value = "/city/{id}/dispensary")
    public List<Dispensary> getDispensary(@PathVariable String id){
        log.info("GET : Get dispensary for given city :{}",id);
        List<Dispensary> dispensary= dispensaryRepository.findDispensariesByCityId(Long.parseLong(id));
        return dispensary;

    }
}

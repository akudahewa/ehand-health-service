package com.zerotoproduction.bucketlist.controller;

import com.zerotoproduction.bucketlist.domain.City;
import com.zerotoproduction.bucketlist.domain.Dispensary;
import com.zerotoproduction.bucketlist.exception.InternalServerException;
import com.zerotoproduction.bucketlist.exception.ResourceNotFoundException;
import com.zerotoproduction.bucketlist.repository.ICityRepository;
import com.zerotoproduction.bucketlist.repository.IDispensaryRepository;
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

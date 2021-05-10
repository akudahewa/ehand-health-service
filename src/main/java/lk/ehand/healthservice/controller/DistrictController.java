package lk.ehand.healthservice.controller;

import lk.ehand.healthservice.domain.District;
import lk.ehand.healthservice.exception.InternalServerException;
import lk.ehand.healthservice.exception.ResourceNotFoundException;
import lk.ehand.healthservice.repository.IDistrictRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
@Slf4j
public class DistrictController {

    private IDistrictRepository districtRepository;

    DistrictController(IDistrictRepository districtRepository){
        this.districtRepository =districtRepository;
    }

    @PostMapping(value = "/district", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<District> saveDistrict(@RequestBody @Valid District district ){
        log.info("POST -> Create District :{}",district);
        return new ResponseEntity<>(districtRepository.save(district), HttpStatus.CREATED);
    }

    @GetMapping(value = "/district",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<District>> getDistricts(){
        log.info("GET -> Get Districts ");
        return ResponseEntity.ok().body(districtRepository.findAll());
    }

    @PutMapping(value = "/district/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public District updateDistrict(@PathVariable String id,@RequestBody District district){
        log.info("PUT -> Update District {}",district);
        return districtRepository.findById(Long.parseLong(id)).map(dis ->{
            dis.setName(district.getName());
            return districtRepository.save(dis);
        }).orElseThrow(()->new InternalServerException("Could not update the district"));
    }

    @DeleteMapping(value = "district/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDistricts(@PathVariable String id){
        log.info("DELETE -> Delete district -id:{}",id);
        districtRepository.deleteById(Long.parseLong(id));
        return new  ResponseEntity<>("successfully deleted ", HttpStatus.OK);
    }

}

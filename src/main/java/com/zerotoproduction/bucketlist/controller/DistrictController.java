package com.zerotoproduction.bucketlist.controller;

import com.zerotoproduction.bucketlist.domain.District;
import com.zerotoproduction.bucketlist.exception.InternalServerException;
import com.zerotoproduction.bucketlist.repository.IDistrictRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DistrictController {

    private final IDistrictRepository districtRepository;

    /**
     *  create distrcit in persistence layer
     * @param district
     * @return created district object
     */
    @PostMapping(value = "/district", produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<District> saveDistrict(@RequestBody @Valid District district ){
        log.info("POST -> create district ");

        return new ResponseEntity<>(districtRepository.save(district), HttpStatus.CREATED);
    }

    /**
     * get all districts in sri lanka, it hard-coded country
     * @return list of district object
     * no parameter required
     */
    @GetMapping(value = "/district",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<District>> getDistricts(){
        log.info("GET -> Get Districts ");
        System.out.println("ddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        return ResponseEntity.ok().body(districtRepository.findAll());
    }

    /**
     * update district object for given id
     * @param id
     * @param district
     * @return updtaed district object
     */
    @PutMapping(value = "/district/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public District updateDistrict(@PathVariable String id,@RequestBody District district){
        log.info("PUT -> Update District {}",district);
        return districtRepository.findById(Long.parseLong(id)).map(dis ->{
            dis.setName(district.getName());
            return districtRepository.save(dis);
        }).orElseThrow(()->new InternalServerException("Could not update the district"));
    }

    /**
     * delete district
     * @param id
     * @return success message for delete object
     */
    @DeleteMapping(value = "district/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDistricts(@PathVariable String id){
        log.info("DELETE -> Delete district -id:{}",id);
        districtRepository.deleteById(Long.parseLong(id));
        return new  ResponseEntity<>("successfully deleted ", HttpStatus.OK);
    }

}

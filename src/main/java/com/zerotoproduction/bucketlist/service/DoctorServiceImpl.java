package com.zerotoproduction.bucketlist.service;

import com.zerotoproduction.bucketlist.domain.DoctorDispensary;
import com.zerotoproduction.bucketlist.repository.IDispensaryRepository;
import com.zerotoproduction.bucketlist.repository.IDoctorDispensaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements IDoctorService {

    private IDispensaryRepository dispensaryRepository;
    private IDoctorDispensaryRepository doctorDispensaryRepository;

    @Autowired
    public DoctorServiceImpl(IDispensaryRepository dispensaryRepository,
                             IDoctorDispensaryRepository doctorDispensaryRepository){
        this.dispensaryRepository =dispensaryRepository;
        this.doctorDispensaryRepository = doctorDispensaryRepository;
    }
    @Override
    public List<DoctorDispensary> getDoctorByCity(Long cityId) {
       List<DoctorDispensary> doctorDispensaryList=
               doctorDispensaryRepository.findAllByDispensary(cityId);

       return doctorDispensaryList;


    }
}

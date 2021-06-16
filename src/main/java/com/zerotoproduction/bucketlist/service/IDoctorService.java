package com.zerotoproduction.bucketlist.service;

import com.zerotoproduction.bucketlist.domain.DoctorDispensary;

import java.util.List;

public interface IDoctorService {

    public List<DoctorDispensary> getDoctorByCity(Long cityId);
}

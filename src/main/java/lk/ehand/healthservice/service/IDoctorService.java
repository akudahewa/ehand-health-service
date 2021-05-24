package lk.ehand.healthservice.service;

import lk.ehand.healthservice.domain.DoctorDispensary;

import java.util.List;

public interface IDoctorService {

    public List<DoctorDispensary> getDoctorByCity(Long cityId);
}

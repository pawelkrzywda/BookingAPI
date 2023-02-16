package com.booking.repository;

import com.booking.entity.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PatientDao extends CrudRepository<Patient, Long> {
    @Override
    Patient save(Patient patient);

    Optional<Patient> findById(long id);

    @Override
    List<Patient> findAll();
}
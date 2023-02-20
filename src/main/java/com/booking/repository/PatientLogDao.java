package com.booking.repository;

import com.booking.entity.PatientLog;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface PatientLogDao extends CrudRepository<PatientLog, Long> {
    @Override
    PatientLog save(PatientLog patientLog);

    Optional<PatientLog> findById(long id);

    @Override
    List<PatientLog> findAll();
}
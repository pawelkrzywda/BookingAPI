package com.booking.repository;

import com.booking.entity.DoctorLog;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface DoctorLogDao extends CrudRepository<DoctorLog, Long> {
    @Override
    DoctorLog save (DoctorLog doctorLog);

    Optional<DoctorLog> findById(long id);

    @Override
    List<DoctorLog> findAll();
}
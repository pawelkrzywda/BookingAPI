package com.booking.repository;

import com.booking.entity.Doctor;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface DoctorDao extends CrudRepository<Doctor, Long> {
    @Override
    Doctor save (Doctor doctor);

    Optional<Doctor> findById(long id);

    @Override
    List<Doctor> findAll();
}
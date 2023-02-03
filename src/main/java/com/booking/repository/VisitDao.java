package com.booking.repository;

import com.booking.entity.Visit;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface VisitDao extends CrudRepository<Visit, Long> {
    @Override
    Visit save(Visit visit);

    Optional<Visit> findById(long id);
}
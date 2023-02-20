package com.booking.repository;

import com.booking.entity.VisitLog;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface VisitLogDao extends CrudRepository<VisitLog, Long> {
    @Override
    List<VisitLog> findAll();

    @Override
    VisitLog save(VisitLog visitLog);

    Optional<VisitLog> findById(long id);
}
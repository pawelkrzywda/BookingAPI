package com.booking.repository;

import com.booking.entity.OpinionLog;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface OpinionLogDao extends CrudRepository<OpinionLog, Long> {
    @Override
    OpinionLog save(OpinionLog opinionLog);

    Optional<OpinionLog> findById(long id);

    @Override
    List<OpinionLog> findAll();
}
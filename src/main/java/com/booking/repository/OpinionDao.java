package com.booking.repository;

import com.booking.entity.Opinion;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface OpinionDao extends CrudRepository<Opinion, Long> {
    @Override
    Opinion save(Opinion opinion);

    Optional<Opinion> findById(long id);

    @Override
    List<Opinion> findAll();
}
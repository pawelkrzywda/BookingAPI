package com.booking.repository;


import com.booking.entity.Holiday;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface HolidayDao extends CrudRepository<Holiday, Long> {
    @Override
    Holiday save (Holiday holiday);

    Optional<Holiday> findById(long id);
}
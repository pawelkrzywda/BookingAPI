package com.booking.repository;

import com.booking.entity.HolidayLog;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface HolidayLogDao extends CrudRepository<HolidayLog, Long> {
    @Override
    HolidayLog save (HolidayLog holidayLog);

    Optional<HolidayLog> findById(long id);

    @Override
    List<HolidayLog> findAll();
}
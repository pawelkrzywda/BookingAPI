package com.booking.service;

import com.booking.domain.VisitDto;
import com.booking.entity.Visit;
import com.booking.exception.DoctorNotFoundException;
import com.booking.exception.PatientNotFoundException;
import com.booking.exception.VisitNotFoundException;
import com.booking.mapper.VisitMapper;
import com.booking.repository.VisitDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitMapper visitMapper;
    private final VisitDao visitDao;

    public void createVisit(VisitDto visitDto) throws NoSuchAlgorithmException, IOException, PatientNotFoundException, DoctorNotFoundException {
        Visit visit = visitMapper.mapToVisit(visitDto);
        visitDao.save(visit);
    }

    public Visit getVisit(Long id) throws VisitNotFoundException {
        return visitDao.findById(id).orElseThrow(VisitNotFoundException::new);
    }

    public List<Visit> getAllVisits(){
        return visitDao.findAll();
    }

    public Visit saveVisit(Visit visit){
        return visitDao.save(visit);
    }

    public void deleteVisit(Long id) throws VisitNotFoundException {
        Optional<Visit> visit = visitDao.findById(id);
        visitDao.delete(visit.orElseThrow(VisitNotFoundException::new));
    }
}

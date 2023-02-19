package com.booking;

import com.booking.domain.VisitDto;
import com.booking.entity.Visit;
import com.booking.exception.DoctorNotFoundException;
import com.booking.exception.PatientNotFoundException;
import com.booking.exception.VisitNotFoundException;
import com.booking.mapper.VisitMapper;
import com.booking.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/visits")
public class VisitController {
    private final VisitService visitService;
    private final VisitMapper visitMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createVisit(@RequestBody VisitDto visitDto) throws NoSuchAlgorithmException, IOException, PatientNotFoundException, DoctorNotFoundException {
        visitService.createVisit(visitDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VisitDto> getVisit(@PathVariable long id) throws VisitNotFoundException {
        Visit visit = visitService.getVisit(id);
        VisitDto visitDto = visitMapper.mapToVisitDto(visit);
        return ResponseEntity.ok(visitDto);
    }

    @GetMapping
    public ResponseEntity<List<VisitDto>> getVisits() {
        List<Visit> visits = visitService.getAllVisits();
        return ResponseEntity.ok(visitMapper.mapToVisitDtoList(visits));
    }

    @PutMapping
    public ResponseEntity<VisitDto> updateVisit(@RequestBody VisitDto visitDto) throws PatientNotFoundException, DoctorNotFoundException{
        Visit visit = visitMapper.mapToVisit(visitDto);
        Visit updatedVisit = visitService.saveVisit(visit);
        VisitDto savedVisit = visitMapper.mapToVisitDto(updatedVisit);
        return ResponseEntity.ok(savedVisit);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<VisitDto> deleteVisit(@PathVariable long id) throws VisitNotFoundException {
        visitService.deleteVisit(id);
        return ResponseEntity.ok().build();
    }
}
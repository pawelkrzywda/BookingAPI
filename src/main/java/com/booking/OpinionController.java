package com.booking;

import com.booking.domain.DoctorDto;
import com.booking.domain.OpinionDto;
import com.booking.entity.Doctor;
import com.booking.entity.Opinion;
import com.booking.exception.DoctorNotFoundException;
import com.booking.exception.OpinionNotFoundException;
import com.booking.exception.PatientNotFoundException;
import com.booking.mapper.OpinionMapper;
import com.booking.service.OpinionService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/opinions")
public class OpinionController {
    private final OpinionService opinionService;
    private final OpinionMapper opinionMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOpinion(@RequestBody OpinionDto opinionDto) throws NoSuchAlgorithmException, IOException, PatientNotFoundException, DoctorNotFoundException {
        opinionService.createOpinion(opinionDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OpinionDto> getOpinion(@PathVariable long id) throws OpinionNotFoundException {
        Opinion opinion = opinionService.getOpinion(id);
        OpinionDto opinionDto = opinionMapper.mapToOpinionDto(opinion);
        return ResponseEntity.ok(opinionDto);
    }

    @GetMapping(value = "/doctor/{id}")
    public ResponseEntity<List<OpinionDto>> getOpinions(@PathVariable long doctorId) throws DoctorNotFoundException {
        List<Opinion> opinions = opinionService.getOpinionsForDoctor(doctorId);
        return ResponseEntity.ok(opinionMapper.mapToOpinionDtoList(opinions));
    }

    @PutMapping
    public ResponseEntity<OpinionDto> updateOpinion(@RequestBody OpinionDto opinionDto) throws PatientNotFoundException, DoctorNotFoundException{
        Opinion opinion = opinionMapper.mapToOpinion(opinionDto);
        Opinion updatedOpinion = opinionService.saveOpinion(opinion);
        OpinionDto savedOpinion = opinionMapper.mapToOpinionDto(updatedOpinion);
        return ResponseEntity.ok(savedOpinion);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<OpinionDto> deleteOpinion(@PathVariable long id) throws OpinionNotFoundException {
        opinionService.deleteOpinion(id);
        return ResponseEntity.ok().build();
    }
}
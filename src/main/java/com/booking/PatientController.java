package com.booking;

import com.booking.domain.PatientDto;
import com.booking.entity.Patient;
import com.booking.exception.PatientNotFoundException;
import com.booking.mapper.PatientMapper;
import com.booking.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/patients")
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPatient(@RequestBody PatientDto patientDto) throws NoSuchAlgorithmException, IOException {
        patientService.createPatient(patientDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable long id) throws PatientNotFoundException {
        Patient patient = patientService.getPatient(id);
        PatientDto patientDto = patientMapper.mapToPatientDto(patient);
        return ResponseEntity.ok(patientDto);
    }

    @PutMapping
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto) {
        Patient patient = patientMapper.mapToPatient(patientDto);
        Patient updatedPatient = patientService.savePatient(patient);
        PatientDto savedPatient = patientMapper.mapToPatientDto(updatedPatient);
        return ResponseEntity.ok(savedPatient);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<PatientDto> deletePatient(@PathVariable long id) throws PatientNotFoundException {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}
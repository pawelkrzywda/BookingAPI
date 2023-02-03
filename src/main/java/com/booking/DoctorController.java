package com.booking;

import com.booking.domain.DoctorDto;
import com.booking.entity.Doctor;
import com.booking.exception.DoctorNotFoundException;
import com.booking.mapper.DoctorMapper;
import com.booking.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createDoctor(@RequestBody DoctorDto doctorDto) throws NoSuchAlgorithmException, IOException {
        doctorService.createDoctor(doctorDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable long id) throws DoctorNotFoundException {
        Doctor doctor = doctorService.getDoctor(id);
        DoctorDto doctorDto = doctorMapper.mapToDoctorDto(doctor);
        return ResponseEntity.ok(doctorDto);
    }

    @PutMapping
    public ResponseEntity<DoctorDto> updateDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorMapper.mapToDoctor(doctorDto);
        Doctor updatedDoctor = doctorService.saveDoctor(doctor);
        DoctorDto savedDoctor = doctorMapper.mapToDoctorDto(updatedDoctor);
        return ResponseEntity.ok(savedDoctor);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DoctorDto> deleteDoctor(@PathVariable long id) throws DoctorNotFoundException {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }
}
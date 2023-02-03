package com.booking.service;

import com.booking.domain.PatientDto;
import com.booking.entity.Patient;
import com.booking.exception.PatientNotFoundException;
import com.booking.mapper.PatientMapper;
import com.booking.repository.PatientDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientMapper patientMapper;
    private final PatientDao patientDao;

    public void createPatient(PatientDto patientDto) throws NoSuchAlgorithmException, IOException {
        Patient patient = patientMapper.mapToPatient(patientDto);

        patientDao.save(patient);
    }

    public Patient getPatient(Long id) throws PatientNotFoundException {
        return patientDao.findById(id).orElseThrow(PatientNotFoundException::new);
    }

    public Patient savePatient(Patient patient){
        return patientDao.save(patient);
    }

    public void deletePatient(Long id) throws PatientNotFoundException {
        Optional<Patient> patient = patientDao.findById(id);
        patientDao.delete(patient.orElseThrow(PatientNotFoundException::new));
    }
}

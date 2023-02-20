package com.booking.service;

import com.booking.domain.DoctorDto;
import com.booking.entity.Doctor;
import com.booking.exception.DoctorNotFoundException;
import com.booking.mapper.DoctorMapper;
import com.booking.repository.DoctorDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorMapper doctorMapper;
    private final DoctorDao doctorDao;

    public void createDoctor(DoctorDto doctorDto) throws NoSuchAlgorithmException, IOException {
        Doctor doctor = doctorMapper.mapToDoctor(doctorDto);
        doctorDao.save(doctor);
    }

    public Doctor getDoctor(Long id) throws DoctorNotFoundException {
        return doctorDao.findById(id).orElseThrow(DoctorNotFoundException::new);
    }

    public List<Doctor> getAllDoctors(){
        return doctorDao.findAll();
    }

    public Doctor saveDoctor(Doctor doctor){
        return doctorDao.save(doctor);
    }

    public void deleteDoctor(Long id) throws DoctorNotFoundException {
        Optional<Doctor> doctor = doctorDao.findById(id);
        doctorDao.delete(doctor.orElseThrow(DoctorNotFoundException::new));
    }

    public Doctor findDoctor(long id) throws DoctorNotFoundException {
        return doctorDao.findById(id).orElseThrow(DoctorNotFoundException::new);
    }
}
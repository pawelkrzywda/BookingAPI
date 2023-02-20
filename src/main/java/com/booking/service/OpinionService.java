package com.booking.service;

import com.booking.domain.OpinionDto;
import com.booking.entity.Doctor;
import com.booking.entity.Opinion;
import com.booking.exception.DoctorNotFoundException;
import com.booking.exception.OpinionNotFoundException;
import com.booking.exception.PatientNotFoundException;
import com.booking.mapper.OpinionMapper;
import com.booking.repository.DoctorDao;
import com.booking.repository.OpinionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpinionService {
    private final OpinionMapper opinionMapper;
    private final OpinionDao opinionDao;
    private final OpinionLogService opinionLogService;
    private final DoctorDao doctorDao;

    public void createOpinion(OpinionDto opinionDto) throws NoSuchAlgorithmException, IOException, PatientNotFoundException, DoctorNotFoundException {
        Opinion opinion = opinionMapper.mapToOpinion(opinionDto);
        opinionDao.save(opinion);
        opinionLogService.createOpinionLog(opinion);

        //updating rating for Doctor entity
        List<Opinion> opinions = opinionDao.findByDoctorId(opinion.getDoctor().getId());
        double newRating=0;
        for(int i=0; i<opinions.size(); i++){
            newRating = newRating + opinions.get(i).getRating();
        }
        if(opinions.size()!=0){
            newRating = newRating/opinions.size();
        }
        Optional<Doctor> optDoctor = doctorDao.findById(opinion.getDoctor().getId());
        if(optDoctor.isPresent()){
            Doctor doctor = optDoctor.get();
            doctor.setRating(newRating);
            doctorDao.save(doctor);
        }
    }

    public Opinion getOpinion(Long id) throws OpinionNotFoundException {
        return opinionDao.findById(id).orElseThrow(OpinionNotFoundException::new);
    }

    public List<Opinion> getOpinionsForDoctor(Long doctorId){
        return opinionDao.findByDoctorId(doctorId);
    }

    public Opinion saveOpinion(Opinion opinion){
        opinionLogService.createOpinionLog(opinion);
        return opinionDao.save(opinion);
    }

    public void deleteOpinion(Long id) throws OpinionNotFoundException {
        Optional<Opinion> opinion = opinionDao.findById(id);
        opinionDao.delete(opinion.orElseThrow(OpinionNotFoundException::new));
    }
}
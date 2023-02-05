package com.booking.service;

import com.booking.domain.OpinionDto;
import com.booking.entity.Opinion;
import com.booking.exception.DoctorNotFoundException;
import com.booking.exception.OpinionNotFoundException;
import com.booking.exception.PatientNotFoundException;
import com.booking.mapper.OpinionMapper;
import com.booking.repository.OpinionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpinionService {
    private final OpinionMapper opinionMapper;
    private final OpinionDao opinionDao;

    public void createOpinion(OpinionDto opinionDto) throws NoSuchAlgorithmException, IOException, PatientNotFoundException, DoctorNotFoundException {
        Opinion opinion = opinionMapper.mapToOpinion(opinionDto);
        opinionDao.save(opinion);
    }

    public Opinion getOpinion(Long id) throws OpinionNotFoundException {
        return opinionDao.findById(id).orElseThrow(OpinionNotFoundException::new);
    }

    public Opinion saveOpinion(Opinion opinion){
        return opinionDao.save(opinion);
    }

    public void deleteOpinion(Long id) throws OpinionNotFoundException {
        Optional<Opinion> opinion = opinionDao.findById(id);
        opinionDao.delete(opinion.orElseThrow(OpinionNotFoundException::new));
    }
}
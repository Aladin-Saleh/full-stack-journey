package com.aladin.quizzapp.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aladin.quizzapp.dto.ParticipationDTO;
import com.aladin.quizzapp.exception.EntityNotFoundException;
import com.aladin.quizzapp.exception.ErrorCodes;
import com.aladin.quizzapp.exception.InvalidEntityException;
import com.aladin.quizzapp.repository.ParticipationRepository;
import com.aladin.quizzapp.services.ParticipationService;
import com.aladin.quizzapp.validator.ParticipationValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ParticipationServiceImplementation implements ParticipationService {

    private ParticipationRepository participationRepository;

    public ParticipationServiceImplementation(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    @Override
    public List<ParticipationDTO> findByQuizzId(Integer id) {

        if (id == null) {
            log.error("Id provided is not valid !");
            return null;
        }

        List<ParticipationDTO> participation = this.participationRepository.findByQuizzId(id);

        if (participation.isEmpty()) {
            log.error("No participation found for the quizz with id : " + id);
            throw new EntityNotFoundException("No participation found for the quizz with id : ",
                    ErrorCodes.PARTICIPATION_NOT_FOUND);
        }

        return participation;
    }

    @Override
    public List<ParticipationDTO> findByStudentId(Integer id) {

        if (id == null) {
            log.error("Id provided is not valid !");
            return null;
        }

        List<ParticipationDTO> participation = this.participationRepository.findByStudentId(id);

        if (participation.isEmpty()) {
            log.error("No participation found for the student with id : " + id);
            throw new EntityNotFoundException("No participation found for the student with id : ",
                    ErrorCodes.PARTICIPATION_NOT_FOUND);
        }

        return participation;
    }

    @Override
    public ParticipationDTO save(ParticipationDTO participationDTO) {
        List<String> errors = ParticipationValidator.validate(participationDTO);

        if (participationDTO == null) {
            log.error("No participation provided !");
            return null;
        }

        if (!errors.isEmpty()) {
            log.error("Participation is not valid : " + participationDTO);
            throw new InvalidEntityException("Participation is not valid : " + errors);
        }

        return ParticipationDTO
                .fromEntity(this.participationRepository.save(ParticipationDTO.toEntity(participationDTO)));

    }

    @Override
    public ParticipationDTO update(ParticipationDTO participationDTO) {
        List<String> errors = ParticipationValidator.validate(participationDTO);
        ParticipationDTO isParticipationExist = ParticipationDTO.fromEntity(this.participationRepository.findById(participationDTO.getId()).orElse(null));


        if (isParticipationExist == null) {
            log.error("Participation provided not valid !");
            throw new InvalidEntityException("Participation provided not valid !", ErrorCodes.PARTICIPATION_NOT_VALID);
        }

        if (!errors.isEmpty()) {
            log.error("Participation is not valid : " + participationDTO);
            throw new InvalidEntityException("Participation is not valid : " + errors);
        }

        return ParticipationDTO
                .fromEntity(this.participationRepository.save(ParticipationDTO.toEntity(participationDTO)));

    }

}

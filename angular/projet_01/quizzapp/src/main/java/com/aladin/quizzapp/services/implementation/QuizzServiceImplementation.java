package com.aladin.quizzapp.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.aladin.quizzapp.dto.QuizzDTO;
import com.aladin.quizzapp.exception.EntityNotFoundException;
import com.aladin.quizzapp.exception.ErrorCodes;
import com.aladin.quizzapp.exception.InvalidEntityException;
import com.aladin.quizzapp.models.QuizzEntity;
import com.aladin.quizzapp.repository.QuizzRepository;
import com.aladin.quizzapp.services.QuizzService;
import com.aladin.quizzapp.validator.QuizzValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuizzServiceImplementation implements QuizzService {

    private QuizzRepository quizzRepository;

    public QuizzServiceImplementation(QuizzRepository quizzRepository) {
        this.quizzRepository = quizzRepository;
    }

    @Override
    public List<QuizzDTO> findAll() {
        return this.quizzRepository.findAll()
                .stream()
                .map(QuizzDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuizzDTO> findByTitle(String title) {
        if (!StringUtils.hasLength(title)) {
            log.error("No title provided !");
            return null;
        }

        List<QuizzDTO> dto = this.quizzRepository.findQuizzEntitiesByTitle(title)
                .stream()
                .map(QuizzDTO::fromEntity)
                .collect(Collectors.toList());

        if (dto.isEmpty()) {
            log.error("No quizz found with this title");
            throw new EntityNotFoundException("No quizz found with this title", ErrorCodes.QUIZZ_NOT_FOUND);
        }

        return dto;
    }

    @Override
    public QuizzDTO findById(Integer id) {

        if (id == null) {
            log.error("No id provided !");
            return null;
        }

        Optional<QuizzEntity> dto = this.quizzRepository.findById(id);

        return Optional.of(QuizzDTO.fromEntity(dto.get())).orElseThrow(
                () -> new EntityNotFoundException("No quizz found with this id", ErrorCodes.QUIZZ_NOT_FOUND));

    }

    @Override
    public QuizzDTO save(QuizzDTO quizzDTO) {
        List<String> errors = QuizzValidator.validate(quizzDTO);

        if (!errors.isEmpty()) {
            log.error("Quizz do not have a valid format !", errors);
            throw new InvalidEntityException("Quizz do not have a valid format !", ErrorCodes.QUIZZ_NOT_VALID, errors);
        }
        return QuizzDTO.fromEntity(quizzRepository.save(QuizzDTO.toEntity(quizzDTO)));
    }


    @Override
    public QuizzDTO update(QuizzDTO quizzDTO) {
        
        List<String> errors = QuizzValidator.validate(quizzDTO);
        QuizzDTO isQuizzExist = QuizzDTO.fromEntity(this.quizzRepository.findById(QuizzDTO.toEntity(quizzDTO).getId()).orElse(null));

        if (isQuizzExist == null) {
            log.error("The quizz provided doesn't exist !", quizzDTO.getId());
            throw new InvalidEntityException("The quizz provided doesn't exist !", ErrorCodes.QUIZZ_NOT_VALID);
        }

        if (!errors.isEmpty()) {
            log.error("Quizz do not have a valid format !", errors);
            throw new InvalidEntityException("Quizz do not have a valid format !", ErrorCodes.QUIZZ_NOT_VALID, errors);
        }

        return QuizzDTO.fromEntity(quizzRepository.save(QuizzDTO.toEntity(quizzDTO)));
    }

    @Override
    public List<QuizzDTO> findByTeacher(String teacherName) {

        if (!StringUtils.hasLength(teacherName)) {
            log.error("No id provided !");
            return null;
        }

        List<QuizzDTO> dto = this.quizzRepository.findQuizzEntitiesByTeacherUsername(teacherName).stream()
                .map(QuizzDTO::fromEntity).collect(Collectors.toList());

        if (dto.isEmpty()) {
            log.error("No quizz found for this teacher");
            throw new EntityNotFoundException("No quizz found for this teacher", ErrorCodes.QUIZZ_NOT_FOUND);
        }

        return dto;
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("No id provided !");
            return;
        }

        QuizzDTO isQuizzExist = QuizzDTO.fromEntity(this.quizzRepository.findById(id).orElse(null));

        if (isQuizzExist == null) {
            log.error("The quizz provided doesn't exist !", id);
            throw new InvalidEntityException("The quizz provided doesn't exist !", ErrorCodes.QUIZZ_NOT_VALID);
        }

        this.quizzRepository.deleteById(id);
    }



}

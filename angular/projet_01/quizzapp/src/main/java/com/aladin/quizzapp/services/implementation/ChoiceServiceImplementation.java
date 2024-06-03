package com.aladin.quizzapp.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aladin.quizzapp.dto.ChoiceDTO;
import com.aladin.quizzapp.exception.ErrorCodes;
import com.aladin.quizzapp.exception.InvalidEntityException;
import com.aladin.quizzapp.repository.ChoiceRepository;
import com.aladin.quizzapp.services.ChoiceService;
import com.aladin.quizzapp.validator.ChoiceValidator;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ChoiceServiceImplementation implements ChoiceService {

    private ChoiceRepository choiceRepository;

    @Override
    public List<ChoiceDTO> findChoicesByQuestionId(Integer id) {
        if (id == null) {
            log.error("Quizz id is null");
            return null;
        }

        List<ChoiceDTO> choices = this.choiceRepository.findChoicesByQuestionId(id);

        if (choices.isEmpty()) {
            log.error("No choices found for quizz with id {}", id);
            throw new InvalidEntityException("No choices found for quizz with id " + id, ErrorCodes.CHOICE_NOT_FOUND);
        }

        return choices;
    }

    @Override
    public ChoiceDTO save(ChoiceDTO choice) {
        List<String> errors = ChoiceValidator.validate(choice);

        if (!errors.isEmpty()) {
            log.error("Choice is not valid {}", choice);
            throw new InvalidEntityException("Choice is not valid", ErrorCodes.CHOICE_NOT_VALID);
        }

        return ChoiceDTO.fromEntity(this.choiceRepository.save(ChoiceDTO.toEntity(choice)));

    }

    @Override
    public ChoiceDTO update(ChoiceDTO choice) {
        List<String> errors = ChoiceValidator.validate(choice);
        ChoiceDTO isChoiceExist = ChoiceDTO.fromEntity(this.choiceRepository.findById(choice.getId()).orElse(null));

        if (isChoiceExist == null) {
            log.error("Choice doesn't exist !", choice.getId());
            throw new InvalidEntityException("Choice doesn't exist !", ErrorCodes.CHOICE_NOT_FOUND);
        }

        if (!errors.isEmpty()) {
            log.error("Choice is not valid {}", choice);
            throw new InvalidEntityException("Choice is not valid", ErrorCodes.CHOICE_NOT_VALID);
        }

        return ChoiceDTO.fromEntity(this.choiceRepository.save(ChoiceDTO.toEntity(choice)));
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Choice id is null");
            return;
        }

        ChoiceDTO isChoiceExist = ChoiceDTO.fromEntity(this.choiceRepository.findById(id).orElse(null));

        if (isChoiceExist == null) {
            log.error("Choice doesn't exist !", id);
            throw new InvalidEntityException("Choice doesn't exist !", ErrorCodes.CHOICE_NOT_FOUND);
        }

        this.choiceRepository.deleteById(id);

    }
    
}

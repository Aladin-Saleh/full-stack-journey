package com.aladin.quizzapp.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aladin.quizzapp.dto.QuestionDTO;
import com.aladin.quizzapp.exception.EntityNotFoundException;
import com.aladin.quizzapp.exception.ErrorCodes;
import com.aladin.quizzapp.exception.InvalidEntityException;
import com.aladin.quizzapp.repository.QuestionRepository;
import com.aladin.quizzapp.services.QuestionService;
import com.aladin.quizzapp.validator.QuestionValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionServiceImplementation implements QuestionService {

    private QuestionRepository questionRepository;

    @Override
    public List<QuestionDTO> findByQuizz(Integer id) {
        if (id == null) {
            log.error("Id provided is not valid !");
            return null;
        }

        List<QuestionDTO> question = this.questionRepository.findByQuizzId(id);

        if (question.isEmpty()) {
            log.error("No question found for this quizzId !");
            throw new EntityNotFoundException("No question found for this quizzId !", ErrorCodes.QUESTION_NOT_FOUND);
        }

        return question;
    }

    @Override
    public QuestionDTO save(QuestionDTO questionDTO) {

        List<String> errors = QuestionValidator.validate(questionDTO);

        if (!errors.isEmpty()) {
            log.error("Question do not have a valid format !", errors);
            throw new InvalidEntityException("Question do not have a valid format !", ErrorCodes.QUESTION_NOT_VALID, errors);
        }

        return QuestionDTO.fromEntity(this.questionRepository.save(QuestionDTO.toEntity(questionDTO)));

    }

    @Override
    public QuestionDTO update(QuestionDTO questionDTO) {
        List<String> errors         = QuestionValidator.validate(questionDTO);
        QuestionDTO isQuestionExist = QuestionDTO.fromEntity(this.questionRepository.findById(questionDTO.getId()).orElse(null));

        if (isQuestionExist == null) {
            log.error("Question doesn't exist !", questionDTO.getId());
            throw new InvalidEntityException("Question doesn't exist !", ErrorCodes.QUESTION_NOT_VALID);
        }

        if (!errors.isEmpty()) {
            log.error("Question do not have a valid format !", errors);
            throw new InvalidEntityException("Question do not have a valid format !", ErrorCodes.QUESTION_NOT_VALID, errors);
        }

        return QuestionDTO.fromEntity(this.questionRepository.save(QuestionDTO.toEntity(questionDTO)));

    }

    @Override
    public void delete(Integer id) {
        
        if (id == null) {
            log.error("No id provided !");
            return;
        }
        
        QuestionDTO isQuestionExist = QuestionDTO.fromEntity(this.questionRepository.findById(id).orElse(null));

        if (isQuestionExist == null) {
            log.error("Question doesn't exist !", id);
            throw new InvalidEntityException("Question doesn't exist !", ErrorCodes.QUESTION_NOT_FOUND);
        }

        this.questionRepository.deleteById(id);
    }

}

package com.aladin.quizzapp.controllers.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.aladin.quizzapp.dto.ChoiceDTO;

import static com.aladin.quizzapp.utils.Constant.APP_ROOT;


public interface ChoiceAPI {

    @GetMapping(value = APP_ROOT + "/choices/quizz/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ChoiceDTO> findChoicesByQuestionId(Integer id);

    @PostMapping(value = APP_ROOT + "/choices/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ChoiceDTO save(ChoiceDTO choice);

    @PutMapping(value = APP_ROOT + "/choices/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ChoiceDTO update(ChoiceDTO choice);

    @DeleteMapping(value = APP_ROOT + "/choices/delete/{id}")
    void delete(Integer id);
}

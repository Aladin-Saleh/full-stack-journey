package com.aladin.quizzapp.controllers.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aladin.quizzapp.dto.QuestionDTO;

import static com.aladin.quizzapp.utils.Constant.APP_ROOT;

public interface QuestionAPI  {




    @GetMapping(value = APP_ROOT + "/question/{quizzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<QuestionDTO> findByQuizz(@PathVariable("quizzId") Integer id);

    @PostMapping(value = APP_ROOT + "/question/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    QuestionDTO save(@RequestBody QuestionDTO questionDTO);

    @PutMapping(value = APP_ROOT + "/question/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    QuestionDTO update(@RequestBody QuestionDTO questionDTO);

    @DeleteMapping(value = APP_ROOT + "/question/{quizzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("quizzId") Integer id);
}

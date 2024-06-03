package com.aladin.quizzapp.controllers.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aladin.quizzapp.dto.QuizzDTO;

import static com.aladin.quizzapp.utils.Constant.APP_ROOT;





public interface QuizzAPI {
    
    @GetMapping(value = APP_ROOT + "/quizz/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<QuizzDTO> findAll();

    @GetMapping(value = APP_ROOT + "/quizz/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<QuizzDTO> findByTitle(@PathVariable("title") String title);

    @GetMapping(value = APP_ROOT + "/quizz/{teacherName}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<QuizzDTO> findByTeacher(@PathVariable("teacherName") String name);

    @GetMapping(value = APP_ROOT + "/quizz/{quizzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    QuizzDTO findById(@PathVariable("quizzId") Integer id);

    @PostMapping(value = APP_ROOT + "/quizz/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    QuizzDTO save(@RequestBody QuizzDTO quizzDTO);

    @PutMapping(value = APP_ROOT + "/quizz/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    QuizzDTO update(QuizzDTO quizzDTO);

    @DeleteMapping(value = APP_ROOT + "/quizz/delete/{quizzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("quizzId") Integer id);


    
}

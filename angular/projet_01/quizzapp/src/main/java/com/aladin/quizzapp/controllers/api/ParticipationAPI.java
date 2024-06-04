package com.aladin.quizzapp.controllers.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aladin.quizzapp.dto.ParticipationDTO;

import static com.aladin.quizzapp.utils.Constant.APP_ROOT;


public interface ParticipationAPI {
    

    @GetMapping(value = APP_ROOT + "/participation/{quizzId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ParticipationDTO> findByQuizzId(@PathVariable("quizzId") Integer id);

    @GetMapping(value = APP_ROOT + "/participation/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ParticipationDTO> findByStudentId(@PathVariable("studentId") Integer id);

    @PostMapping(value = APP_ROOT + "/participation/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ParticipationDTO save(@RequestBody ParticipationDTO participationDTO);

    @PutMapping(value = APP_ROOT + "/participation/update",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ParticipationDTO update(@RequestBody ParticipationDTO participationDTO);
}

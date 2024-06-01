package com.aladin.quizzapp.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aladin.quizzapp.dto.QuizzDTO;
import com.aladin.quizzapp.services.QuizzService;



@Service
public class QuizzServiceImplementation implements QuizzService {

    @Override
    public List<QuizzDTO> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public List<QuizzDTO> findByTitle(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByTitle'");
    }

    @Override
    public QuizzDTO findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }






}

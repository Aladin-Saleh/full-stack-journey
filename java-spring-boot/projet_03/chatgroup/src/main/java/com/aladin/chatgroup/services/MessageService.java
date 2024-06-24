package com.aladin.chatgroup.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.aladin.chatgroup.models.Message;
import com.aladin.chatgroup.repositories.MessageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository repository;
    

    public Message sendMessage(Message message) {
        // TODO: Ajouter des validations (validator)
        return this.repository.save(message);
    }

    public List<Message> getMessagesByGroupId(String id) {
        // TODO: Ajouter des validations (validator) et vérifier si l'objet existe
        return this.repository.findByGroupId(id);
    }
    
}

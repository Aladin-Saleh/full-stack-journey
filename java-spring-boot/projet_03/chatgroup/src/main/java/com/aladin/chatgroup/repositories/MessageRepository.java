package com.aladin.chatgroup.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aladin.chatgroup.models.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findByGroupId(String id);

    
}

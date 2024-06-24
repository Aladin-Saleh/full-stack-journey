package com.aladin.chatgroup.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.aladin.chatgroup.models.ChatGroup;
import com.aladin.chatgroup.models.User;
import com.aladin.chatgroup.repositories.ChatGroupRepository;
import com.aladin.chatgroup.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatGroupService {

    private final ChatGroupRepository repository;
    private final UserRepository userRepository;

    public ChatGroup create(ChatGroup chatGroup) {
        // TODO: Ajouter des validations (validator)
        return repository.save(chatGroup);
    }

    public ChatGroup update(ChatGroup chatGroup) {
        // TODO: Ajouter des validations (validator) et vérifier si l'objet existe
        return repository.save(chatGroup);
    }

    public void delete(String id) {
        ChatGroup chatGroup = this.repository.findById(id).orElseThrow();
        this.repository.delete(chatGroup);
    }

    public ChatGroup findById(String id) {
        return this.repository.findById(id).orElseThrow();
    }

    public List<ChatGroup> findAll() {
        return this.repository.findAll();
    }

    public void addUserToGroup(String userId, String chatGroupId) {
        log.info("User id : " + userId);
        User user = this.userRepository.findByUsername(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        // TODO: Ajouter des validations (validator) et vérifier si l'objet existe
        this.repository.addUserToChatGroup(user, chatGroupId);
    }

    

}

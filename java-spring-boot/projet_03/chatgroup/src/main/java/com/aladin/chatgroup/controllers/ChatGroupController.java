package com.aladin.chatgroup.controllers;
import java.util.List;

// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aladin.chatgroup.models.ChatGroup;
import com.aladin.chatgroup.services.ChatGroupService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor
@RequestMapping("/api/chatgroups")
public class ChatGroupController {

    private final ChatGroupService service;

    @PostMapping("/create")
    public ChatGroup createChatGroup(@RequestBody ChatGroup chatGroup) {
        return this.service.create(chatGroup);
    }


    @GetMapping("/all")
    public List<ChatGroup> findAll() {
        return this.service.findAll();
    }


    @GetMapping("/find/{id}")
    public ChatGroup findById(@PathVariable String id) {
        return this.service.findById(id);
    }
    
}

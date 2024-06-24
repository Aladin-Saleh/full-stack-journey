package com.aladin.chatgroup.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aladin.chatgroup.models.Message;
import com.aladin.chatgroup.services.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService service;

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return service.sendMessage(message);
    }

    @GetMapping("/group/{groupId}")
    public List<Message> getMessagesByGroupId(@PathVariable String groupId) {
        return service.getMessagesByGroupId(groupId);
    }
}

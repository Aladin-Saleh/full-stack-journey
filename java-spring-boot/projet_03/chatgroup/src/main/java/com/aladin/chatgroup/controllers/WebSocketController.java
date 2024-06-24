package com.aladin.chatgroup.controllers;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.aladin.chatgroup.models.Message;
import com.aladin.chatgroup.services.ChatGroupService;
import com.aladin.chatgroup.services.MessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messageTemplate;

    private final ChatGroupService chatGroupService;
    private final MessageService messageService;


    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        String destination = "/topic/" + message.getGroupId();
        messageTemplate.convertAndSend(destination, message);

        this.messageService.sendMessage(message);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        message.setTimestamp(LocalDateTime.now().toString());
        message.setContent(message.getSenderId() + " joined!");
        headerAccessor.getSessionAttributes().put("username", message.getSenderId());
        String destination = "/topic/" + message.getGroupId();
        messageTemplate.convertAndSend(destination, message);

        this.chatGroupService.addUserToGroup(message.getSenderId(), message.getGroupId());
        




    }
}

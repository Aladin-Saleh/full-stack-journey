package com.chat.chat.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public") // C'est la destination de la réponse (la queue de sortie)
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }



    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public") // C'est la destination de la réponse (la queue de sortie)
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender()); // On ajoute le nom d'utilisateur dans la session
        return chatMessage;
    }

}

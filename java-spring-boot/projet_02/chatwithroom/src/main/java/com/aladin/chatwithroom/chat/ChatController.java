package com.aladin.chatwithroom.chat;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {

        ChatMessage savedMessage = this.chatMessageService.saveMessage(chatMessage);
        this.messagingTemplate.convertAndSendToUser(savedMessage.getRecipientId(), "/queue/messages",
                ChatNotification.builder()
                        .id(savedMessage.getId())
                        .senderId(savedMessage.getSenderId())
                        .recipientId(savedMessage.getRecipientId())
                        .content(savedMessage.getContent())
                        .build()); // On envoie un message à l'utilisateur destinataire dans la file d'attente /queue/messages

    }



    // Cette méthode permet de récupérer les messages d'une conversation entre deux utilisateurs
    // Par exemple lorsque l'utilisateur A envoie un message à l'utilisateur B, l'utilisateur B pourra récupérer le message en appelant cette méthode
    // Par exemple lorsque l'utilisateur A se connecte, il pourra récupérer tous les messages qu'il a reçu de l'utilisateur B
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> getChatMessages(@PathVariable("senderId") String senderId, @PathVariable("recipientId") String recipientId) {
        return ResponseEntity.ok(this.chatMessageService.findChatMessages(senderId, recipientId)); 
    }

}

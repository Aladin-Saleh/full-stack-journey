package com.aladin.chatwithroom.chatroom;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class ChatRoom {



    @Id
    private String id; // Il s'agit de l'identifiant de la conversation entre deux utilisateurs

    private String chatId; // Il s'agit de l'identifiant de la conversation entre deux utilisateurs

    private String senderId; // Il s'agit de l'identifiant de l'utilisateur qui envoie le message au recipientId
 
    private String recipientId; // Il s'agit de l'identifiant de l'utilisateur qui reçoit le message du senderId

    
}

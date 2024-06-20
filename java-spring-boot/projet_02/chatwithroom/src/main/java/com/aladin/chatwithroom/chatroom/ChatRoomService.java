package com.aladin.chatwithroom.chatroom;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;


    // Cette méthode permet de trouver une conversation entre deux utilisateurs, donc deux les deux ChatRoom
    // Elle cherche la conversation que l'utilisateur qui envoie le message a avec l'utilisateur qui reçoit le message (en commun)
    // Si la conversation n'existe pas, elle sera créée
    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createIfNotExist) {

        return this.chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
        .map(ChatRoom::getChatId)
        .or(() -> {
            if (createIfNotExist) {
                var chatId = createChat(senderId, recipientId);
                return Optional.of(chatId);
            }
            
            return Optional.empty();

        });
        
    }


    // Cette méthode permet de créer une conversation entre deux utilisateurs, donc deux ChatRoom
    // Une chatRoom pour l'utilisateur qui envoie le message et une autre pour l'utilisateur qui reçoit le message, cependant les deux chatRoom ont le même chatId
    // Si on aurait voulu avoir une conversation entre plusieurs utilisateurs, on aurait pu ajouter une liste de senderId et recipientId dans la classe ChatRoom à la place de senderId et recipientId
    public String createChat(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoom senderRecipient = ChatRoom.builder()
        .chatId(chatId)
        .senderId(senderId)
        .recipientId(recipientId)
        .build();

        ChatRoom recipientSender = ChatRoom.builder()
        .chatId(chatId)
        .senderId(senderId)
        .recipientId(recipientId)
        .build();


        this.chatRoomRepository.save(senderRecipient);
        this.chatRoomRepository.save(recipientSender);
        return chatId;
    }
    
}

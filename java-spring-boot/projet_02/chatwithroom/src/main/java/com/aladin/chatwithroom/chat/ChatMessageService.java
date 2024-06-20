package com.aladin.chatwithroom.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aladin.chatwithroom.chatroom.ChatRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    

    private final ChatMessageRepository chatMessageRepository;

    private final ChatRoomService chatRoomService;

    public ChatMessage saveMessage(ChatMessage chatMessage) {


        var chatId = this.chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
        .orElseThrow(
            () -> new IllegalArgumentException("Chat Room not found")
        );

        chatMessage.setChatId(chatId);
        this.chatMessageRepository.save(chatMessage);
        return chatMessage;
    }


    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = this.chatRoomService.getChatRoomId(senderId, recipientId, false);

        return chatId.map(this.chatMessageRepository::findByChatId).orElse(new ArrayList<>());
        
    }


}

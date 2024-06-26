package com.aladin.chatwithroom.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {
    

    private String id; // ChatId

    private String senderId;

    private String recipientId;

    private String content;


}

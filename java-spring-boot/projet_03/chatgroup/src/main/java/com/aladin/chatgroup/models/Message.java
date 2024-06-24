package com.aladin.chatgroup.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "messages")
public class Message {
    
    @Id
    private String id;
    private String content;
    private String groupId;
    private String senderId;
    private String timestamp;

}

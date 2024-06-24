package com.aladin.chatgroup.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "chatgroups")
public class ChatGroup {

    @Id
    private String id;
    private String name;
    private String adminId;
    private List<User> members;
    
}

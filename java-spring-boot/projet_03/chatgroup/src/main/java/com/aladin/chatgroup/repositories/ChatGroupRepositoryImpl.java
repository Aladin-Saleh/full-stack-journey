package com.aladin.chatgroup.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.aladin.chatgroup.models.ChatGroup;
import com.aladin.chatgroup.models.User;

@Component
public class ChatGroupRepositoryImpl implements ChatGroupRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addUserToChatGroup(User userId, String chatGroupId) {
        Query query = new Query(Criteria.where("id").is(chatGroupId));
        Update update = new Update().addToSet("members", userId);
        mongoTemplate.updateFirst(query, update, ChatGroup.class);
    }
}

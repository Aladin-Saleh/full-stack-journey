package com.aladin.chatgroup.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.aladin.chatgroup.models.ChatGroup;

public interface ChatGroupRepository extends MongoRepository<ChatGroup, String>, ChatGroupRepositoryCustom  {

}

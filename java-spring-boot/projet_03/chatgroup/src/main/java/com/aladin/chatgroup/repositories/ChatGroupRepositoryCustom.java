package com.aladin.chatgroup.repositories;

import com.aladin.chatgroup.models.User;

public interface ChatGroupRepositoryCustom {
    void addUserToChatGroup(User userId, String chatGroupId);
}

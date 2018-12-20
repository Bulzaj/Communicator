package com.sadsoft.communicator.dao;

import com.sadsoft.communicator.model.Conversation;
import com.sadsoft.communicator.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Boolean existsByUniqueConversationsName(String conversationsName);
    Optional<Conversation> findByUniqueConversationsName(String conversationsName);
}

package com.sadsoft.communicator.dao;

import com.sadsoft.communicator.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Boolean existsByUniqueConversationsName(String conversationsName);
    Optional<Conversation> findByUniqueConversationsName(String conversationsName);
}

package com.sadsoft.communicator.model.dto;

import com.sadsoft.communicator.model.Conversation;
import com.sadsoft.communicator.model.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ConversationResponseDto {

    private String conversationsName;
    private Set<MessageDto> messages = new HashSet<>();

    public ConversationResponseDto(Conversation conversation) {
        conversationsName = conversation.getUniqueConversationsName();
        for (Message message:conversation.getMessages()) {
            messages.add(new MessageDto(message));
        }
    }
}

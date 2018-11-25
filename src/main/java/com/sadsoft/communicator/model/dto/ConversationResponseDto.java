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

    private Set<MessageResponseDto> messages = new HashSet<>();

    public ConversationResponseDto(Conversation conversation) {
        for (Message message:conversation.getMessages()) {
            messages.add(new MessageResponseDto(message));
        }
    }
}

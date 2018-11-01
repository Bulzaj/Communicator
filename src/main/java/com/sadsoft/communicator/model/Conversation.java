package com.sadsoft.communicator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NaturalId
    @JsonIgnore
    private String uniqueConversationsName;

    @OneToMany(
            mappedBy = "conversation",
            fetch = FetchType.EAGER
    )
    private Set<Message> messages = new HashSet<>();

    public Conversation(String uniqueConversationsName) {
        this.uniqueConversationsName = uniqueConversationsName;
    }

    public void sendMessage(Message message) {
        messages.add(message);
        message.setConversation(this);
    }
}

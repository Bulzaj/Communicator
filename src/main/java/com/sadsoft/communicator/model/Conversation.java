package com.sadsoft.communicator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @JsonIgnore
    private String uniqueConversationsName;

    @ManyToMany
    private Set<User> participants = new HashSet<>();

    @OneToMany(
            mappedBy = "conversation",
            fetch = FetchType.EAGER
    )
    private List<Message> messages = new ArrayList<>();

    public Conversation(String uniqueConversationsName, User... participants) {
        this.uniqueConversationsName = uniqueConversationsName;
        for (User participant:participants) {
            this.participants.add(participant);
        }
    }

    public void sendMessage(Message message) {
        messages.add(message);
        message.setConversation(this);
    }
}

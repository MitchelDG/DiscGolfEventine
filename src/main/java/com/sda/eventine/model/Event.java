package com.sda.eventine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    Integer id;

    @JsonProperty(value = "event_name")
    String name;
    //TODO set max char restriction to 500 characters
    @JsonProperty(value = "description")
    String description;

    @OneToOne
    @JsonProperty(value = "owner_id")
    Integer ownerId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty(value = "participants_id")
    List<Integer> participantsId;

}

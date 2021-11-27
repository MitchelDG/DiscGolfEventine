/*-kage com.sda.eventine.model;
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "event")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true)
public class Event {


    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty(value = "name")
    private String name;
    //TODO set max char restriction to 500 characters OR/AND let user know in text/input field
    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "created_at")
    private LocalDateTime createdAt;

    private LocalDateTime start;

    private LocalDateTime end;

    @OneToOne
    @JsonProperty(value = "owner")
    @JoinColumn(referencedColumnName = "name")
    private User owner;

    @OneToMany
    @JsonProperty(value = "participants")
    @JoinColumn(referencedColumnName = "id")
    private List<User> participants;

    @OneToMany
    @JsonProperty(value = "comments")
    @JoinColumn(referencedColumnName = "id")
    private List<Comment> comments;
}

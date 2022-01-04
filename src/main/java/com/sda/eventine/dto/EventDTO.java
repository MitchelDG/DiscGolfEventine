
package com.sda.eventine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EventDTO {

    private String name;
    private String description;
    private Long capacity;
    private String start;
    private String end;


}

package com.webculcate.event.reservation.service.core.model.external.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CapacityUpdateRequest {

    private Long scheduledEventId;

    private Integer capacity;

    private String capacityUpdateType;

}

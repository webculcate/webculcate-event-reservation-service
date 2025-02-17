package com.webculcate.event.reservation.service.core.model.dto.eventreservation.transaction;

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
public class CapacityReductionRollbackEvent {

    private Long scheduledEventId;

    private Integer creditCapacity;

}

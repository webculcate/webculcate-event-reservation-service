package com.webculcate.event.reservation.service.core.model.dto.eventreservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventReservationCreationRequest {

    @NotNull
    private Long scheduledEventId;

    @NotNull
    private Long purchasedBy;

    @NotNull
    @NotEmpty
    private Set<Long> customerIds;

}

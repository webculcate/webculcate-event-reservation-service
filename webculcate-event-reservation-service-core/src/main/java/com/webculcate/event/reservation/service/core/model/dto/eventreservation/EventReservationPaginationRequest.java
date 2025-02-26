package com.webculcate.event.reservation.service.core.model.dto.eventreservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventReservationPaginationRequest {

    @NotNull
    private Long scheduledEventId;

    @NotNull
    private Integer pageIndex;

    @NotNull
    private Integer pageSize;

}

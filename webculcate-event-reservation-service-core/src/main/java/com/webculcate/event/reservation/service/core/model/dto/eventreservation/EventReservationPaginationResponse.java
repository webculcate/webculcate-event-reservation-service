package com.webculcate.event.reservation.service.core.model.dto.eventreservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventReservationPaginationResponse {

    List<ScheduledEventReservationDto> reservations;

    private Integer pageIndex;

    private Integer pageSize;

}

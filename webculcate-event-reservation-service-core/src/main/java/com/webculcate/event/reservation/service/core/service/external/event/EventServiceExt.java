package com.webculcate.event.reservation.service.core.service.external.event;

import com.webculcate.event.reservation.service.core.model.external.event.CapacityUpdateRequest;
import com.webculcate.event.reservation.service.core.model.external.event.CapacityUpdateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.webculcate.event.reservation.service.core.constant.external.CapacityUpdateType.DECREASE_CAPACITY;
import static com.webculcate.event.reservation.service.core.utility.ServiceHelper.nullHandledExtraction;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceExt {

    private final EventServiceClient eventServiceClient;


    public CapacityUpdateResponse reduceCapacity(Long scheduledEventId, Integer capacity) {
        ResponseEntity<CapacityUpdateResponse> response = eventServiceClient.updateCapacity(new CapacityUpdateRequest(scheduledEventId, capacity, DECREASE_CAPACITY));
        return nullHandledExtraction(response::getBody)
                .orElse(new CapacityUpdateResponse(false));
    }
}

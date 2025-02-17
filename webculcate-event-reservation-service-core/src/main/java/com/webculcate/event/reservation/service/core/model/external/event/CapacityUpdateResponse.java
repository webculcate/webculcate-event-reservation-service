package com.webculcate.event.reservation.service.core.model.external.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CapacityUpdateResponse {

    private Long scheduledEventId;

    private Integer capacityAfterUpdate;

    private Boolean success;

    public CapacityUpdateResponse(Boolean success) {
        this.success = success;
    }

    public boolean hasFailed() {
        if (isNull(success) || !getSuccess())
            return true;
        else
            return false;
    }

}

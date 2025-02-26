package com.webculcate.event.reservation.service.core.service.external.event;

import com.webculcate.event.reservation.service.core.model.external.event.CapacityUpdateRequest;
import com.webculcate.event.reservation.service.core.model.external.event.CapacityUpdateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.WEBCULCATE_EVENT_SERVICE;

@FeignClient(name = WEBCULCATE_EVENT_SERVICE)
public interface EventServiceClient {

    @PutMapping("event/schedule/v1/capacity")
    ResponseEntity<CapacityUpdateResponse> updateCapacity(@RequestBody CapacityUpdateRequest request);

}

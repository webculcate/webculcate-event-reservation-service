package com.webculcate.event.reservation.service.core.service.eventreservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.webculcate.event.reservation.service.core.constant.EventReservationStrategyType.DEFAULT_EVENT_RESERVATION_SERVICE;
import static com.webculcate.event.reservation.service.core.constant.EventReservationStrategyType.EVENT_RESERVATION_SERVICE_PROXY;
import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.PROXY_ENABLED;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventReservationServiceManager {

    private final Map<String, IEventReservationService> serviceMap;

    @Value(PROXY_ENABLED)
    private boolean proxyEnabled;

    public IEventReservationService getEventReservationService() {
        if (proxyEnabled)
            return serviceMap.get(EVENT_RESERVATION_SERVICE_PROXY);
        else
            return serviceMap.get(DEFAULT_EVENT_RESERVATION_SERVICE);
    }
}

package com.webculcate.event.reservation.service.core.service.eventreservation.transaction;

import com.webculcate.event.reservation.service.core.exception.eventreservation.InvalidCapacityException;
import com.webculcate.event.reservation.service.core.exception.eventreservation.PaymentFailedException;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationCreationRequest;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationPaginationRequest;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.transaction.EventReservationRollbackDto;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentResponse;
import com.webculcate.event.reservation.service.core.model.entity.eventreservation.ScheduledEventReservation;
import com.webculcate.event.reservation.service.core.model.external.event.CapacityUpdateResponse;
import com.webculcate.event.reservation.service.core.repository.eventreservation.EventReservationRepository;
import com.webculcate.event.reservation.service.core.service.eventreservation.factory.IEventReservationFactory;
import com.webculcate.event.reservation.service.core.service.external.event.EventServiceExt;
import com.webculcate.event.reservation.service.core.service.payment.PaymentManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.webculcate.event.reservation.service.core.constant.ScheduledEventReservationStatus.CONFIRMED;
import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.STRING_UNDERSCORE;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventReservationTransactionService {

    private final EventReservationRepository repository;

    private final EventServiceExt eventServiceExt;

    private final PaymentManager paymentManager;

    private final IEventReservationFactory factory;

    private final ApplicationEventPublisher publisher;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ScheduledEventReservation> createEventReservationTransaction(EventReservationCreationRequest request, Integer capacity) {
        EventReservationRollbackDto rollbackDto = new EventReservationRollbackDto();
        CapacityUpdateResponse reductionResponse = eventServiceExt.reduceCapacity(request.getScheduledEventId(), capacity);
        if (reductionResponse.hasFailed())
            throw new InvalidCapacityException();
        else
            rollbackDto.setCapacityUpdateResponse(reductionResponse);
        List<ScheduledEventReservation> reservations = generateAndSaveEventReservations(request);
        PaymentResponse paymentResponse = paymentManager.adaptAndPay(request);
        if (paymentResponse.isNotSuccessful())
            throw new PaymentFailedException();
        else
            rollbackDto.setPaymentResponse(paymentResponse);
        reservations = confirmEventReservations(reservations);
        publisher.publishEvent(rollbackDto);
        return reservations;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ScheduledEventReservation> generateAndSaveEventReservations(EventReservationCreationRequest request) {
        List<ScheduledEventReservation> reservations = generateEventReservation(request.getCustomerIds(), request.getScheduledEventId());
        repository.saveAll(reservations);
        return reservations;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ScheduledEventReservation> generateEventReservation(Set<Long> customerIds, Long scheduledEventId) {
        return customerIds.stream()
                .map(customerId -> generateEventReservation(customerId, scheduledEventId))
                .toList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ScheduledEventReservation generateEventReservation(Long customerId, Long scheduledEventId) {
        return factory.generateScheduledEventReservation(customerId, scheduledEventId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ScheduledEventReservation> confirmEventReservations(List<ScheduledEventReservation> reservations) {
        reservations.forEach(reservation -> {
            reservation.setStatus(CONFIRMED);
            reservation.setConfirmationKey(
                    String.join(STRING_UNDERSCORE,
                            reservation.getScheduledEventId().toString(),
                            reservation.getCustomerId().toString(),
                            CONFIRMED.name())
            );
        });
        reservations = repository.saveAll(reservations);
        return reservations;
    }

    @Transactional(readOnly = true)
    public List<ScheduledEventReservation> getEventReservationList(EventReservationPaginationRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPageIndex(), request.getPageSize());
        Page<ScheduledEventReservation> resultList = repository.findAll(pageRequest);
        return resultList.toList();
    }

}

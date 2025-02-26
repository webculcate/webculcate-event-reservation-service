package com.webculcate.event.reservation.service.core.service.eventreservation.impl;

import com.webculcate.event.reservation.service.core.model.dto.eventreservation.ScheduledEventReservationDto;
import com.webculcate.event.reservation.service.core.model.entity.eventreservation.ScheduledEventReservation;
import com.webculcate.event.reservation.service.core.model.external.user.UserDto;
import com.webculcate.event.reservation.service.core.service.eventreservation.IScheduledEventReservationDtoMapper;
import com.webculcate.event.reservation.service.core.service.external.user.UserServiceExt;
import com.webculcate.event.reservation.service.core.service.payment.IPaymentDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultScheduledEventReservationDtoMapper implements IScheduledEventReservationDtoMapper {

    private final IPaymentDtoMapper paymentDtoMapper;

    private final UserServiceExt userService;

    @Override
    public List<ScheduledEventReservationDto> mapToScheduledEventReservationDto(List<ScheduledEventReservation> reservations) {
        return reservations.stream()
                .map(this::mapToScheduledEventReservationDto)
                .toList();
    }

    @Override
    public ScheduledEventReservationDto mapToScheduledEventReservationDto(ScheduledEventReservation reservation) {
        ScheduledEventReservationDto reservationDto = ScheduledEventReservationDto.initializeBlankScheduledEventReservationDto();
        copyProperties(reservation, reservationDto);
        if (nonNull(reservation.getTimeLog()) && nonNull(reservationDto.getTimeLog()))
            copyProperties(reservation.getTimeLog(), reservationDto.getTimeLog());
        Optional<UserDto> optionalUserDto = userService.resolveUser(reservation.getCustomerId());
        if (optionalUserDto.isPresent())
            reservationDto.setCustomer(optionalUserDto.get());
        reservationDto.setPayment(paymentDtoMapper.mapToPaymentDto(reservation.getPayment()));
        return reservationDto;
    }

}

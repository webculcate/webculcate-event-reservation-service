package com.webculcate.event.reservation.service.core.service.payment.impl;

import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentDto;
import com.webculcate.event.reservation.service.core.model.entity.payment.Payment;
import com.webculcate.event.reservation.service.core.model.external.user.UserDto;
import com.webculcate.event.reservation.service.core.service.external.user.UserServiceExt;
import com.webculcate.event.reservation.service.core.service.payment.IPaymentDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultPaymentDtoMapper implements IPaymentDtoMapper {

    private final UserServiceExt userService;

    @Override
    public PaymentDto mapToPaymentDto(Payment savedPayment) {
        PaymentDto paymentDto = PaymentDto.initializeBlankPaymentDto();
        copyProperties(savedPayment, paymentDto);
        if (nonNull(savedPayment.getTimeLog()) && nonNull(paymentDto.getTimeLog()))
            copyProperties(savedPayment.getTimeLog(), paymentDto.getTimeLog());
        Optional<UserDto> optionalUserDto = userService.resolveUser(savedPayment.getPurchasedBy());
        if (optionalUserDto.isPresent())
            paymentDto.setPurchasedBy(optionalUserDto.get());
        return paymentDto;
    }

}

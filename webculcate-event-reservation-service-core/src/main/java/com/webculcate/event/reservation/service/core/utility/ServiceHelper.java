package com.webculcate.event.reservation.service.core.utility;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.function.Supplier;

@UtilityClass
public class ServiceHelper {

    public static <T> Optional<T> nullHandledExtraction(Supplier<T> methodChain) {
        try {
            return Optional.ofNullable(methodChain.get());
        } catch (NullPointerException exception) {
            return Optional.empty();
        }
    }
}
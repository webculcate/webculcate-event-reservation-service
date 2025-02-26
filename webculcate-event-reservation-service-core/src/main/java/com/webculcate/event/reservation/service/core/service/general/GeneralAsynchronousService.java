package com.webculcate.event.reservation.service.core.service.general;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.DEFAULT_TASK_EXECUTOR;

@Slf4j
@Service
public class GeneralAsynchronousService {

    @Async(DEFAULT_TASK_EXECUTOR)
    public <I,O> CompletableFuture<O> assignTask(Function<I,O> task, I input) {
        try {
            O output = task.apply(input);
            return CompletableFuture.completedFuture(output);
        } catch (Exception exception) {
            log.error("Exception : ", exception);
            return CompletableFuture.failedFuture(exception);
        }
    }

}

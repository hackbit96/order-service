package com.tc.application.service;

import com.azure.storage.queue.QueueAsyncClient;
import com.google.gson.Gson;
import com.tc.domain.model.Order;
import com.tc.shared.exception.ApiResponseCode;
import com.tc.shared.exception.IntegrationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final QueueAsyncClient queueClient;

    @Override
    public Mono<Void> enqueueOrder(Order order) {
        String message = new Gson().toJson(order);
        return queueClient.sendMessage(message)
                .then()
                .onErrorMap(ex -> {
                    log.error("Error al enviar mensaje a la cola", ex);
                    return new IntegrationException(ApiResponseCode.ORDER_QUEUE_ERROR, ex);
                });
    }
}

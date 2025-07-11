package com.tc.service;

import com.azure.storage.queue.QueueAsyncClient;
import com.google.gson.Gson;
import com.tc.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final QueueAsyncClient queueClient;

    public Mono<Void> enqueueOrder(Order order) {
        String message = new Gson().toJson(order);
        return queueClient.sendMessage(message)
                .then();
    }
}

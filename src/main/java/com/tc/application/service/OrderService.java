package com.tc.application.service;

import com.tc.domain.model.Order;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Void> enqueueOrder(Order order);

}

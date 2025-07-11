package com.tc.service;

import com.tc.model.request.Order;
import com.tc.shared.dto.ApiResponse;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<Void> enqueueOrder(Order order);

}

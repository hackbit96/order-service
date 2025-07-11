package com.tc.interfaces;

import com.tc.model.request.Order;
import com.tc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Mono<ResponseEntity<Void>> createOrder(@RequestBody Order order) {
        return orderService.enqueueOrder(order)
                .thenReturn(ResponseEntity.ok().build());
    }
}

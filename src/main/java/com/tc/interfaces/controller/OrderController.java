package com.tc.interfaces.controller;

import com.tc.application.service.OrderService;
import com.tc.domain.model.Order;
import com.tc.shared.dto.ApiResponse;
import com.tc.shared.exception.ApiResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Mono<ResponseEntity<ApiResponse>> createOrder(@RequestBody Order order) {
        return orderService.enqueueOrder(order)
                .thenReturn(ResponseEntity.ok(
                        new ApiResponse(ApiResponseCode.ORDER_SUCCESS)
                ));
    }
}

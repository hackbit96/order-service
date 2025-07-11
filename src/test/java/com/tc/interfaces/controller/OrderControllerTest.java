package com.tc.interfaces.controller;

import com.tc.application.mocks.Mock;
import com.tc.application.service.OrderService;
import com.tc.domain.model.Order;
import com.tc.shared.dto.ApiResponse;
import com.tc.shared.exception.ApiResponseCode;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private OrderService orderService;

    @Test
    void createOrder_ShouldReturnSuccessResponse() {
        Mockito.when(orderService.enqueueOrder(Mockito.any(Order.class)))
                .thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/v1/order")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Mock.mockOrder())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ApiResponse.class)
                .value(response -> {
                    assert response.getCode().equals(ApiResponseCode.ORDER_SUCCESS.getCode());
                });

        Mockito.verify(orderService).enqueueOrder(Mockito.any(Order.class));
    }
}
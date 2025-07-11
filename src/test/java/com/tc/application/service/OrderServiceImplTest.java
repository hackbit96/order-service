package com.tc.application.service;

import com.azure.storage.queue.QueueAsyncClient;
import com.tc.application.mocks.Mock;
import com.tc.shared.exception.ApiResponseCode;
import com.tc.shared.exception.IntegrationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceImplTest {

    private QueueAsyncClient queueClient;
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        queueClient = mock(QueueAsyncClient.class);
        orderService = new OrderServiceImpl(queueClient);
    }

    @Test
    void enqueueOrder_ShouldCompleteSuccessfully() {
        when(queueClient.sendMessage(anyString()))
                .thenReturn(Mono.empty());

        StepVerifier.create(orderService.enqueueOrder(Mock.mockOrder()))
                .verifyComplete();

        verify(queueClient).sendMessage(anyString());
    }

    @Test
    void enqueueOrder_ShouldThrowIntegrationException_WhenErrorOccurs() {
        when(queueClient.sendMessage(anyString()))
                .thenReturn(Mono.error(new RuntimeException("Simulated failure")));

        StepVerifier.create(orderService.enqueueOrder(Mock.mockOrder()))
                .expectErrorSatisfies(throwable -> {
                    assert throwable instanceof IntegrationException;
                    IntegrationException ex = (IntegrationException) throwable;
                    assert ex.getCode() == ApiResponseCode.ORDER_QUEUE_ERROR;
                })
                .verify();

        verify(queueClient).sendMessage(anyString());
    }

}
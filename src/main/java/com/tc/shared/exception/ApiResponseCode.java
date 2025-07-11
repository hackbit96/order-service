package com.tc.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiResponseCode {

    ORDER_SUCCESS("ORD-0000", "Order enqueued successfully"),
    ORDER_ALREADY_EXISTS("ORD-0001", "La orden ya fue encolada recientemente"),
    ORDER_QUEUE_ERROR("ORD-1001", "Error al enviar orden a la cola"),
    VALIDATION_ERROR("GEN-0001", "Error de validación de campos"),
    INTERNAL_ERROR("GEN-9999", "Error interno del sistema");


    private final String code;
    private final String message;
}

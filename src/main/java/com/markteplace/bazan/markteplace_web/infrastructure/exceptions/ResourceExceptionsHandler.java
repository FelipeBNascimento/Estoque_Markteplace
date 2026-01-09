package com.markteplace.bazan.markteplace_web.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionsHandler {

    @ExceptionHandler(EstoqueInsuficienteExceptions.class)
    public ResponseEntity<MappeadorExceptions> estoqueInsuficiente(EstoqueInsuficienteExceptions e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        MappeadorExceptions mappeadorExceptions = new MappeadorExceptions(status.value(),
                "estoque não pode ser negativo", e.getMessage());

        return ResponseEntity.status(status).body(mappeadorExceptions);

    }

    @ExceptionHandler(IdNaoEncontrado.class)
    public ResponseEntity<MappeadorExceptions> idNaoEncontrado(IdNaoEncontrado e){

        HttpStatus status = HttpStatus.NOT_FOUND;

        MappeadorExceptions mappeadorExceptions = new MappeadorExceptions(status.value(),
                "Id inexistente", e.getMessage());

        return ResponseEntity.status(status).body(mappeadorExceptions);
    }

    @ExceptionHandler(SaldoInsuficiente.class)
    public ResponseEntity<MappeadorExceptions> saldoInsuficienteResponseEntity(SaldoInsuficiente e){

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        MappeadorExceptions mappeadorExceptions = new MappeadorExceptions(status.value(),
                "Não pode fazer pagamento com saldo negativo", e.getMessage());

        return ResponseEntity.status(status).body(mappeadorExceptions);
    }
}

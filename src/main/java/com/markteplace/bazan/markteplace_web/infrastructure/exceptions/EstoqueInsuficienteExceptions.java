package com.markteplace.bazan.markteplace_web.infrastructure.exceptions;

public class EstoqueInsuficienteExceptions extends RuntimeException{

    public EstoqueInsuficienteExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public EstoqueInsuficienteExceptions(String mensagem){
        super(mensagem);



    }
}

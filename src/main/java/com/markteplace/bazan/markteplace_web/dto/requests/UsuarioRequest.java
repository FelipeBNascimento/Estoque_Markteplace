package com.markteplace.bazan.markteplace_web.dto.requests;

import java.math.BigDecimal;

public record UsuarioRequest(String nome, String email,
                             String senha, BigDecimal saldo) {
}


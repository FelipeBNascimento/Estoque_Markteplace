package com.markteplace.bazan.markteplace_web.dto.responses;

import java.math.BigDecimal;

public record UsuarioResponse(Long id, String nome, String email, BigDecimal saldo, boolean ativo) {
}

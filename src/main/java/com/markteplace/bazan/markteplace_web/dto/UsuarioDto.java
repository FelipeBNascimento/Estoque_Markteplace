package com.markteplace.bazan.markteplace_web.dto;

import java.math.BigDecimal;

public record UsuarioDto(Long id,
                         String nome,
                         String email,
                         String senha,
                         BigDecimal saldo) {
}

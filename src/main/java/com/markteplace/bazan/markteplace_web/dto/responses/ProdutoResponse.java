package com.markteplace.bazan.markteplace_web.dto.responses;

import java.math.BigDecimal;

public record ProdutoResponse (Long id, String nome, BigDecimal preco, Integer quantidade) {
}

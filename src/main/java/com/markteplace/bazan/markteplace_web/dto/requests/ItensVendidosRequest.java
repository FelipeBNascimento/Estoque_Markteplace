package com.markteplace.bazan.markteplace_web.dto.requests;

import java.math.BigDecimal;

public record ItensVendidosRequest(Long id_produto, Integer quantidade,
                                   BigDecimal preco_vendido, String nome_produto) {
}

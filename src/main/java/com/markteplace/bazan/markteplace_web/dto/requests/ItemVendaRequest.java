package com.markteplace.bazan.markteplace_web.dto.requests;

import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.VendaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

public record ItemVendaRequest(Long id_produto, Long id_venda, Integer quantidade,
                               BigDecimal preco_vendido, String nome_produto) {
}

package com.markteplace.bazan.markteplace_web.dto.requests;
import java.math.BigDecimal;

public record ProdutoRequest (String nome, BigDecimal preco, Integer quantidade){
}



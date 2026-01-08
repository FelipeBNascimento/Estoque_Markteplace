package com.markteplace.bazan.markteplace_web.dto.responses;

import java.math.BigDecimal;


public record ItensVendidosResponse(Long id, Integer quantdade, BigDecimal preco,
                                    Long id_Produto, Long id_Venda){}

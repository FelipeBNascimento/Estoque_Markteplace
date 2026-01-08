package com.markteplace.bazan.markteplace_web.dto.responses;

import com.markteplace.bazan.markteplace_web.infrastructure.entity.ItensVendidosEntity;

import java.time.LocalDate;
import java.util.List;

public record VendasResponses (Long id, LocalDate data, ItensVendidosEntity itens) {
}

package com.markteplace.bazan.markteplace_web.dto.requests;

import com.markteplace.bazan.markteplace_web.infrastructure.entity.ItemVendaEntity;

import java.time.LocalDate;

public record VendaRequest(ItemVendaEntity itensVendas, LocalDate data) {
}

package com.markteplace.bazan.markteplace_web.dto.requests;

import com.markteplace.bazan.markteplace_web.infrastructure.entity.ItensVendidosEntity;

import java.time.LocalDate;
import java.util.List;

public record VendaRequest(List<ItensVendidosRequest> itensVendas) {
}

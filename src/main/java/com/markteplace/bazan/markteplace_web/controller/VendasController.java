package com.markteplace.bazan.markteplace_web.controller;

import com.markteplace.bazan.markteplace_web.bussines.VendaService;
import com.markteplace.bazan.markteplace_web.dto.VendasDto;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ItemVendaEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.VendaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vendas")
@CrossOrigin("*")
public class VendasController {

    private final VendaService service;

    @PostMapping("/{id}/{quantidade_vendida}")
    public ResponseEntity<Void> cadastrarVenda(@PathVariable Long id, @PathVariable Integer quantidade_vendida){

        service.realizarVendas(id, quantidade_vendida);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ItemVendaEntity>> mostrarVendas(){

         return ResponseEntity.ok(service.buscarTodasVendas());
    }

    @PostMapping("/realizar-venda")
    public ResponseEntity<Void> realizarVendasMultiplosProdutos (@RequestBody VendasDto vendas){

        service.vendasMultiplosProdutos(vendas);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/variasVendas")

    public ResponseEntity<Void> variasVendas(@RequestBody VendasDto vendas){

        service.variasVendas(vendas);

        return ResponseEntity.ok().build();
    }


}

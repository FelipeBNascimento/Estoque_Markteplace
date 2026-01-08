package com.markteplace.bazan.markteplace_web.controller;

import com.markteplace.bazan.markteplace_web.bussines.VendaService;
import com.markteplace.bazan.markteplace_web.dto.responses.VendasResponses;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ItensVendidosEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vendas")
@CrossOrigin("*")
public class VendasController {

    private final VendaService service;

    @PostMapping("/{id}/{quantidade_vendida}/{id_usuario}")
    public ResponseEntity<VendasResponses> cadastrarVenda(@PathVariable Long id,
                                                              @PathVariable Integer quantidade_vendida,
                                                              @PathVariable Long id_usuario){

        return ResponseEntity.ok(service.vendasUmItem(id, quantidade_vendida, id_usuario));
    }

    @GetMapping
    public ResponseEntity<List<VendasResponses>> mostrarVendas(){

         return ResponseEntity.ok(service.buscarTodasVendas());
    }

//    @PostMapping("/realizar-venda")
//    public ResponseEntity<Void> realizarVendasMultiplosProdutos (@RequestBody VendasDto vendas){
//
//        service.vendasMultiplosProdutos(vendas);
//
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/variasVendas")
//
//    public ResponseEntity<Void> variasVendas(@RequestBody VendasDto vendas){
//
//        service.variasVendas(vendas);
//
//        return ResponseEntity.ok().build();
//    }


}

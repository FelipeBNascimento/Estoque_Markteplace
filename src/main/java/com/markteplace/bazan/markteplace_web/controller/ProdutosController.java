package com.markteplace.bazan.markteplace_web.controller;


import com.markteplace.bazan.markteplace_web.bussines.ProdutosService;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutosController {

    private final ProdutosService service;

    @PostMapping
    public ResponseEntity<Void> criarProduto(@RequestBody ProdutosEntity produtos) {

        service.cadastrarProduto(produtos);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<List<ProdutosEntity>> mostrarEstoque() {


        return ResponseEntity.ok(service.mostrarEstoque());
    }

    @GetMapping("/{id}")

    public ResponseEntity<ProdutosEntity> mostrarProduto(@PathVariable Long id) {

        return ResponseEntity.ok(service.mostrarProduto(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutos(@PathVariable Long id) {

        service.deletarProdutos(id);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarEstoque(@RequestBody ProdutosEntity produto, @PathVariable Long id) {

        service.venderProduto(produto, id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}/preco")
    public ResponseEntity<Void> atualizarPreco(@RequestBody ProdutosEntity produto, @PathVariable Long id) {

        service.atualizarPreco(produto, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/quantidade")

    public ResponseEntity<Void> atualizarQuantidade(@RequestBody ProdutosEntity produto, @PathVariable Long id){

        service.alimentarEstoque(produto, id);
        return ResponseEntity.ok().build();

    }


}

package com.markteplace.bazan.markteplace_web.testes;

import com.markteplace.bazan.markteplace_web.dto.ItensVendidosDto;
import com.markteplace.bazan.markteplace_web.dto.VendasDto;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ItemVendaEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.VendaEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

public class Read_m {


    //Tenho classes entity com nomes
    // produtos essa seria para adicionar produto
    // itemVenda entity essa seria para mostrar o id da venda e os produtos que foramm vendidos
    //VendaEntity  essa mostra a tabela de cada venda mostrando o id e a data da venda
    //

    //Tenhos os repossitorios de cada classe entity

    // as classe service de produtos tema logica de adicionar um produto, visualizar o estoque, atualizar preço, visualizar estoque pelo id
    //deletar algum produto,

    // a classe controller do produto esta assim
//    @PostMapping
//    public ResponseEntity<Void> criarProduto(@RequestBody ProdutosEntity produtos) {
//
//        service.cadastrarProduto(produtos);
//        return ResponseEntity.ok().build();
//
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ProdutosEntity>> mostrarEstoque() {
//
//
//        return ResponseEntity.ok(service.mostrarEstoque());
//    }
//
//    @GetMapping("/{id}")
//
//    public ResponseEntity<ProdutosEntity> mostrarProduto(@PathVariable Long id) {
//
//        return ResponseEntity.ok(service.mostrarProduto(id));
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletarProdutos(@PathVariable Long id) {
//
//        service.deletarProdutos(id);
//
//        return ResponseEntity.ok().build();
//    }
//
//
////    @PutMapping("/{id}")
////    public ResponseEntity<Void> atualizarEstoque(@RequestBody ProdutosEntity produto, @PathVariable Long id) {
////
////        service.venderProduto(produto, id);
////        return ResponseEntity.ok().build();
////    }
//
//
//    @PutMapping("/{id}/preco")
//    public ResponseEntity<Void> atualizarPreco(@RequestBody ProdutosEntity produto, @PathVariable Long id) {
//
//        service.atualizarPreco(produto, id);
//        return ResponseEntity.ok().build();
//    }
//
//    @PutMapping("/{id}/quantidade")
//
//    public ResponseEntity<Void> atualizarQuantidade(@RequestBody ProdutosEntity produto, @PathVariable Long id){
//
//        service.alimentarEstoque(produto, id);
//        return ResponseEntity.ok().build();


    // a de vendas service esta assim

//public List<ItemVendaEntity> buscarTodasVendas() {
//
//    return itensRepositorios.findAll();
//}
//
//    public ItemVendaEntity realizarVendas(Long id, Integer quantidade) {
//
//        ProdutosEntity produto = produtosService.mostrarProduto(id);
//
//        if (produto.getQuantidade() < quantidade) {
//            throw new RuntimeException("Estoque insuficiente para venda");
//        }
//
//        produtosService.atualizarEstoque(id, -quantidade);
//
//        ItemVendaEntity novaVenda = new ItemVendaEntity();
//        novaVenda.setProdutos(produto);
//        novaVenda.setPreco_vendido(produto.getPreco());
//        novaVenda.setQuantidade_vendida(quantidade);
//
//        return itensRepositorios.saveAndFlush(novaVenda);
//
//    }
//
//    public void vendasMultiplosProdutos(VendasDto vendas) {
//
//
//        for (ItensVendidosDto itens : vendas.getItens()) {
//
//            ProdutosEntity produto = produtosService.mostrarProduto(itens.getIdProduto());
//
//            if (produto.getQuantidade() < itens.getQuantidade()) {
//                throw new RuntimeException("Estoque insuficiente para venda");
//            }
//
//            produtosService.atualizarEstoque(itens.getIdProduto(), -itens.getQuantidade());
//
//            ItemVendaEntity novaVenda = new ItemVendaEntity();
//
//            novaVenda.setProdutos(produto);
//            novaVenda.setPreco_vendido(produto.getPreco());
//            novaVenda.setQuantidade_vendida(itens.getQuantidade());
//
//            itensRepositorios.saveAndFlush(novaVenda);
//        }
//    }
//
//    public VendaEntity variasVendas(VendasDto listaVendas) {
//
//        VendaEntity novaVenda = new VendaEntity();
//
//        novaVenda.setData(LocalDateTime.now());
//        vendas_repositorio.saveAndFlush(novaVenda);
//
//
//        for (ItensVendidosDto itens : listaVendas.getItens()) {
//
//            ProdutosEntity produto = produtosService.mostrarProduto(itens.getIdProduto());
//
//            if (produto.getQuantidade() < itens.getQuantidade()) {
//                throw new RuntimeException("Estoque insuficientes");
//            }
//
//            produtosService.atualizarEstoque(itens.getIdProduto(), -itens.getQuantidade());
//
//            ItemVendaEntity itemVendido = new ItemVendaEntity();
//            itemVendido.setProdutos(produto);
//            itemVendido.setNome_produto(produto.getNome());
//            itemVendido.setPreco_vendido(produto.getPreco());
//            itemVendido.setQuantidade_vendida(itens.getQuantidade());
//
//
//            itemVendido.setVenda(novaVenda);
//
//            itensRepositorios.saveAndFlush(itemVendido);
//
//            novaVenda.getItensVenda().add(itemVendido);
//
//
//        }
//
//        return novaVenda;
//
//    }

    // e a controller

//@PostMapping("/{id}/{quantidade_vendida}")
//public ResponseEntity<Void> cadastrarVenda(@PathVariable Long id, @PathVariable Integer quantidade_vendida){
//
//    service.realizarVendas(id, quantidade_vendida);
//    return ResponseEntity.ok().build();
//}
//
//    @GetMapping
//    public ResponseEntity<List<ItemVendaEntity>> mostrarVendas(){
//
//        return ResponseEntity.ok(service.buscarTodasVendas());
//    }
//
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

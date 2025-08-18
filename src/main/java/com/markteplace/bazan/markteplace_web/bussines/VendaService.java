package com.markteplace.bazan.markteplace_web.bussines;

import com.markteplace.bazan.markteplace_web.dto.ItensVendidosDto;
import com.markteplace.bazan.markteplace_web.dto.VendasDto;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ItemVendaEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.VendaEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.repository.ItemVendaRepository;
import com.markteplace.bazan.markteplace_web.infrastructure.repository.VendasRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class VendaService {

    private final ItemVendaRepository itensRepositorios;
    private final ProdutosService produtosService;
    private final VendasRepository vendas_repositorio;


    public List<ItemVendaEntity> buscarTodasVendas() {

        return itensRepositorios.findAll();
    }

    public ItemVendaEntity realizarVendas(Long id, Integer quantidade) {

        ProdutosEntity produto = produtosService.mostrarProduto(id);

        if (produto.getQuantidade() < quantidade) {
            throw new RuntimeException("Estoque insuficiente para venda");
        }

        produtosService.atualizarEstoque(id, -quantidade);

        ItemVendaEntity novaVenda = new ItemVendaEntity();
        novaVenda.setProdutos(produto);
        novaVenda.setPreco_vendido(produto.getPreco());
        novaVenda.setQuantidade_vendida(quantidade);

        return itensRepositorios.saveAndFlush(novaVenda);

    }

    @Transactional
    public void vendasMultiplosProdutos(VendasDto vendas) {


        for (ItensVendidosDto itens : vendas.getItens()){

            ProdutosEntity produto = produtosService.mostrarProduto(itens.getIdProduto());
            if (produto.getQuantidade() < itens.getQuantidade()) {
                throw new EstoqueInsuficienteExceptions("Estoque insuficiente");
            }

        }

        for (ItensVendidosDto itens : vendas.getItens()) {

            ProdutosEntity produto = produtosService.mostrarProduto(itens.getIdProduto());

            produtosService.atualizarEstoque(itens.getIdProduto(), -itens.getQuantidade());

            ItemVendaEntity novaVenda = new ItemVendaEntity();

            novaVenda.setProdutos(produto);
            novaVenda.setPreco_vendido(produto.getPreco());
            novaVenda.setQuantidade_vendida(itens.getQuantidade());

            itensRepositorios.saveAndFlush(novaVenda);
        }
    }

    public VendaEntity variasVendas(VendasDto listaVendas) {

        VendaEntity novaVenda = new VendaEntity();
        novaVenda.setData(LocalDateTime.now());
        vendas_repositorio.saveAndFlush(novaVenda);


        for (ItensVendidosDto itens : listaVendas.getItens()){

            ProdutosEntity produto = produtosService.mostrarProduto(itens.getIdProduto());
            if (produto.getQuantidade() < itens.getQuantidade()) {
                throw new EstoqueInsuficienteExceptions("Estoque insuficiente, quantidade :" + itens.getQuantidade());
            }

        }


        for (ItensVendidosDto itens : listaVendas.getItens()) {

            ProdutosEntity produto = produtosService.mostrarProduto(itens.getIdProduto());


            produtosService.atualizarEstoque(itens.getIdProduto(), -itens.getQuantidade());

            ItemVendaEntity itemVendido = new ItemVendaEntity();
            itemVendido.setProdutos(produto);
            itemVendido.setNome_produto(produto.getNome());
            itemVendido.setPreco_vendido(produto.getPreco());
            itemVendido.setQuantidade_vendida(itens.getQuantidade());


            itemVendido.setVenda(novaVenda);

            itensRepositorios.saveAndFlush(itemVendido);

            novaVenda.getItensVenda().add(itemVendido);


        }

        return novaVenda;

    }

}



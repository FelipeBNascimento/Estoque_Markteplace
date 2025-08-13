package com.markteplace.bazan.markteplace_web.bussines;

import com.markteplace.bazan.markteplace_web.dto.ItensVendidosDto;
import com.markteplace.bazan.markteplace_web.dto.VendasDto;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ItemVendaEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.repository.ItemVendaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VendaService {

    private final ItemVendaRepository repositorio;
    private final ProdutosService produtosService;




    public List<ItemVendaEntity> buscarTodasVendas(){

        return repositorio.findAll();
    }

    public ItemVendaEntity realizarVendas(Long id, Integer quantidade){

        ProdutosEntity produto = produtosService.mostrarProduto(id);

        if (produto.getQuantidade() < quantidade){
            throw new RuntimeException("Estoque insuficiente para venda");
        }

        produtosService.atualizarEstoque(id, -quantidade);

       ItemVendaEntity novaVenda = new ItemVendaEntity();
        novaVenda.setProdutos(produto);
        novaVenda.setPreco_vendido(produto.getPreco());
        novaVenda.setQuantidade_vendida(quantidade);

        return repositorio.saveAndFlush(novaVenda);

    }

    public void vendasMultiplosProdutos (VendasDto vendas){


        for (ItensVendidosDto itens : vendas.getItens()){

            ProdutosEntity produto = produtosService.mostrarProduto(itens.getIdProduto());

            if (produto.getQuantidade() < itens.getQuantidade()){
                throw new RuntimeException("Estoque insuficiente para venda");
            }

            produtosService.atualizarEstoque(itens.getIdProduto(), -itens.getQuantidade());

            ItemVendaEntity novaVenda = new ItemVendaEntity();

            novaVenda.setProdutos(produto);
            novaVenda.setPreco_vendido(produto.getPreco());
            novaVenda.setQuantidade_vendida(itens.getQuantidade());

            repositorio.saveAndFlush(novaVenda);
        }
    }

}

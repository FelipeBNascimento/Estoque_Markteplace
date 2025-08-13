package com.markteplace.bazan.markteplace_web.bussines;

import com.markteplace.bazan.markteplace_web.dto.ItensVendidosDto;
import com.markteplace.bazan.markteplace_web.dto.VendasDto;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ItemVendaEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.repository.ItemVendaRepository;
import com.markteplace.bazan.markteplace_web.infrastructure.repository.VendasRepository;
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


    public List<ItemVendaEntity> buscarTodasVendas() {

        return repositorio.findAll();
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

        return repositorio.saveAndFlush(novaVenda);

    }

    public void fazerVariasVendas(VendasDto vendas) {

        for (ItensVendidosDto item : vendas.getItens()) {

            ProdutosEntity produto = produtosService.mostrarProduto(item.getIdProduto());

            if (produto.getQuantidade() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para venda");
            }

            produtosService.atualizarEstoque(item.getIdProduto(), -item.getQuantidade());

            ItemVendaEntity novaVenda = new ItemVendaEntity();
            novaVenda.setProdutos(produto);
            novaVenda.setPreco_vendido(produto.getPreco());
            novaVenda.setQuantidade_vendida(item.getQuantidade());

            repositorio.saveAndFlush(novaVenda);


        }

    }

    public

}

package com.markteplace.bazan.markteplace_web.bussines;

import com.markteplace.bazan.markteplace_web.dto.ConverterVendas;
import com.markteplace.bazan.markteplace_web.dto.requests.ItensVendidosRequest;
import com.markteplace.bazan.markteplace_web.dto.requests.VendaRequest;
import com.markteplace.bazan.markteplace_web.dto.responses.VendasResponses;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ItensVendidosEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.UsuarioEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.VendaEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.exceptions.EstoqueInsuficienteExceptions;
import com.markteplace.bazan.markteplace_web.infrastructure.exceptions.SaldoInsuficiente;
import com.markteplace.bazan.markteplace_web.infrastructure.repository.ItensVendidosRepository;
import com.markteplace.bazan.markteplace_web.infrastructure.repository.VendasRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class VendaService {

    private final ItensVendidosRepository itensRepositorios;
    private final ProdutosService produtosService;
    private final VendasRepository vendas_repositorio;
    private final ConverterVendas converterVendas;
    private final UsuarioService usuarioService;


    public List<VendasResponses> buscarTodasVendas() {

        return converterVendas.paraListaVendasResponse(vendas_repositorio.findAll());
    }

    @Transactional
    public VendasResponses vendasUmItem(Long idproduto, Integer quantidade, Long idusuario) {

        // criando uma nova venda
        VendaEntity registroNovaVenda = new VendaEntity();
        registroNovaVenda.setData(LocalDate.now());
        vendas_repositorio.saveAndFlush(registroNovaVenda);

        // Buscar o produto para realizar venda
        ProdutosEntity produto = produtosService.buscarProdutopeloId(idproduto);

        // Conferir o estoque de produto
        conferirEntoque(produto, quantidade);

        //Buscar o usuario que irá comprar
        UsuarioEntity usuarioEntity = usuarioService.buscarUsuario(idusuario);

        //Conferindo o saldo para poder realizar a venda
        consultarSaldo(usuarioEntity, produto);

        // Cadastrando um item vendido
        ItensVendidosEntity novaVenda = new ItensVendidosEntity();
        novaVenda.setNome_produto(produto.getNome());
        novaVenda.setPreco_vendido(produto.getPreco());
        novaVenda.setQuantidade_vendida(quantidade);
        novaVenda.setProdutos(produto);
        novaVenda.setVenda(registroNovaVenda);

        // Atualizar estoque depois de ter realizado a venda
        produtosService.atualizarEstoque(idproduto, -quantidade);

        itensRepositorios.saveAndFlush(novaVenda);

        return converterVendas.paraVendaResponse(registroNovaVenda);

    }

    @Transactional
    public VendasResponses vendasMultiplosProdutos(VendaRequest vendas, Long id) {

        // criando uma nova venda
        VendaEntity registroNovaVenda = new VendaEntity();
        registroNovaVenda.setData(LocalDate.now());
        vendas_repositorio.saveAndFlush(registroNovaVenda);

        // buscando usuario
        UsuarioEntity usuario = usuarioService.buscarUsuario(id);


        // fazendo iteração a lista request
        for (ItensVendidosRequest itens : vendas.itensVendas()){
            // buscando produto
            ProdutosEntity produto = produtosService.buscarProdutopeloId(itens.id_produto());
            // conferindo estoque
            conferirEntoque(produto, itens.quantidade());
            // conferindo o saldo
            consultarSaldo(usuario, produto);
            // atualizando o estoque
            produtosService.atualizarEstoque(itens.id_produto(), -itens.quantidade());

            // cadastrando uma nova venda de item
            ItensVendidosEntity novaVenda = new ItensVendidosEntity();
            novaVenda.setProdutos(produto);
            novaVenda.setPreco_vendido(produto.getPreco());
            novaVenda.setQuantidade_vendida(itens.quantidade());
            novaVenda.setVenda(registroNovaVenda);
            itensRepositorios.saveAndFlush(novaVenda);

            registroNovaVenda.getItensVenda().add(novaVenda);
        }

        return converterVendas.paraVendaResponse(registroNovaVenda);
//
//        for (ItensVendidosResponse itens : vendas.getItens()) {
//            ProdutosEntity produto = produtosService.buscarProdutopeloId(itens.getIdProduto());
//            produtosService.atualizarEstoque(itens.getIdProduto(), -itens.getQuantidade());
//            ItensVendidosEntity novaVenda = new ItensVendidosEntity();
//            novaVenda.setProdutos(produto);
//            novaVenda.setPreco_vendido(produto.getPreco());
//            novaVenda.setQuantidade_vendida(itens.getQuantidade());
//            itensRepositorios.saveAndFlush(novaVenda);
//        }
    }
//
//    @Transactional
//    public VendaEntity variasVendas(VendasDto listaVendas) {
//
//        VendaEntity novaVenda = new VendaEntity();
//        novaVenda.setData(LocalDate.now());
//        vendas_repositorio.saveAndFlush(novaVenda);
//
//
//        for (ItensVendidosDto itens : listaVendas.getItens()){
//
//            ProdutosEntity produto = produtosService.buscarProdutopeloId(itens.getIdProduto());
//            if (produto.getQuantidade() < itens.getQuantidade()) {
//                throw new EstoqueInsuficienteExceptions("Estoque insuficiente, quantidade :" + itens.getQuantidade());
//            }
//
//        }
//
//
//        for (ItensVendidosDto itens : listaVendas.getItens()) {
//
//            ProdutosEntity produto = produtosService.buscarProdutopeloId(itens.getIdProduto());
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
//
    public void conferirEntoque(ProdutosEntity produto, Integer quantidade ){

        if (quantidade > produto.getQuantidade()) {
            throw new EstoqueInsuficienteExceptions("Estoque insuficiente para venda");
        }
    }

    public void consultarSaldo(UsuarioEntity usuarioEntity, ProdutosEntity produtos){

        if (produtos.getPreco().compareTo(usuarioEntity.getSaldo())>0){

            throw new SaldoInsuficiente("O valor da compra e maior que o saldo em carteira");

        }
    }

}



package com.markteplace.bazan.markteplace_web.bussines;


import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.repository.ProdutosRepositorios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosRepositorios repositorio;


    public List<ProdutosEntity> mostrarEstoque() {

        return repositorio.findAll();
    }

    public void cadastrarProduto(ProdutosEntity produtos) {

        repositorio.saveAndFlush(produtos);
    }

    public void deletarProdutos(Long id) {

        repositorio.deleteById(id);
    }

    public ProdutosEntity mostrarProduto(Long id) {

        return repositorio.findById(id).orElseThrow(

                () -> new RuntimeException("Id não encontrado")
        );

    }

    public void venderProduto(ProdutosEntity produto, Long id) {

        ProdutosEntity produtoNoBanco = mostrarProduto(id);

        ProdutosEntity produtoNoBancoAtualizado = ProdutosEntity.builder()

                .nome(produtoNoBanco.getNome())
                .preco(produtoNoBanco.getPreco())
                .quantidade((produtoNoBanco.getQuantidade() - produto.getQuantidade()))
                .id(produtoNoBanco.getId())
                .build();

        repositorio.saveAndFlush(produtoNoBancoAtualizado);


    }

    public void atualizarPreco(ProdutosEntity produto, Long id) {

        ProdutosEntity produtoNoBanco = mostrarProduto(id);

        ProdutosEntity produtoNoBancoAtualizado = ProdutosEntity.builder()

                .nome(produtoNoBanco.getNome())
                .preco(produto.getPreco() != null ? produto.getPreco() : produtoNoBanco.getPreco())
                .quantidade(produtoNoBanco.getQuantidade())
                .id(produtoNoBanco.getId())
                .build();

        repositorio.saveAndFlush(produtoNoBancoAtualizado);
    }

    public void alimentarEstoque(ProdutosEntity produto, Long id){

        ProdutosEntity produtoNoBanco = mostrarProduto(id);

        ProdutosEntity produtoNoBancoAtualizado = ProdutosEntity.builder()

           .nome(produtoNoBanco.getNome())
                .preco(produtoNoBanco.getPreco())
                .quantidade(produto.getQuantidade())
                .id(produtoNoBanco.getId())
                .build();

        repositorio.saveAndFlush(produtoNoBancoAtualizado);

    }

    public void atualizarEstoque (Long id, Integer quantidade){

        ProdutosEntity produtoNoBanco = mostrarProduto(id);

        Integer novaQuantidade = produtoNoBanco.getQuantidade() + quantidade;

        if (novaQuantidade < 0) {
            throw new RuntimeException("Estoque não pode ser negativo após a atualização.");
        }

        produtoNoBanco.setQuantidade(novaQuantidade);
        repositorio.saveAndFlush(produtoNoBanco);

    }

}

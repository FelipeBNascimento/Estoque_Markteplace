package com.markteplace.bazan.markteplace_web.bussines;


import com.markteplace.bazan.markteplace_web.dto.ConverterProduto;
import com.markteplace.bazan.markteplace_web.dto.responses.ProdutoResponse;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.exceptions.EstoqueInsuficienteExceptions;
import com.markteplace.bazan.markteplace_web.infrastructure.repository.ProdutosRepositorios;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosRepositorios repositorio;
    private final ConverterProduto converterProduto;


    public List<ProdutoResponse> mostrarEstoque() {

        List<ProdutoResponse> produtos = converterProduto.listaProdutosResponses(
                repositorio.findAll());

        return produtos;
    }

    public void cadastrarProduto(ProdutosEntity produtos) {

        repositorio.saveAndFlush(produtos);
    }

    public void deletarProdutos(Long id) {

        repositorio.deleteById(id);
    }

    public ProdutoResponse mostrarProdutoPeloId(Long id) {

        ProdutoResponse produto = converterProduto.paraProdutoResponse(
                buscarProdutopeloId(id));
        return produto;
    }

    public void atualizarPreco(ProdutosEntity produto, Long id) {

        ProdutosEntity produtoNoBanco = buscarProdutopeloId(id);

        ProdutosEntity produtoNoBancoAtualizado = ProdutosEntity.builder()

                .nome(produtoNoBanco.getNome())
                .preco(produto.getPreco() != null ? produto.getPreco() : produtoNoBanco.getPreco())
                .quantidade(produtoNoBanco.getQuantidade())
                .id(produtoNoBanco.getId())
                .build();

        repositorio.saveAndFlush(produtoNoBancoAtualizado);
    }


    public void atualizarEstoque (Long id, Integer quantidade){

        ProdutosEntity produtoNoBanco = buscarProdutopeloId(id);

        Integer novaQuantidade = produtoNoBanco.getQuantidade() + quantidade;

        verificarQuantidade(novaQuantidade);

        produtoNoBanco.setQuantidade(novaQuantidade);

        repositorio.saveAndFlush(produtoNoBanco);

    }

    public void verificarQuantidade(Integer quantidade){

        // fazer tratamento de erros para que a quantidade não seja negativa
        if (quantidade < 0) {
            throw new EstoqueInsuficienteExceptions("Estoque não pode ser negativo após a atualização.");
        }
    }


    // Metodo criado para usar em outros metodos
    public ProdutosEntity buscarProdutopeloId(Long id){

        ProdutosEntity produto = repositorio.findById(id).orElseThrow(

                () -> new RuntimeException("Id não encontrado")
        );

        return produto;

    }

}

package com.markteplace.bazan.markteplace_web.bussines;


import com.markteplace.bazan.markteplace_web.dto.ConverterProduto;
import com.markteplace.bazan.markteplace_web.dto.converter.Mapper;
import com.markteplace.bazan.markteplace_web.dto.converter.MapperUpdate;
import com.markteplace.bazan.markteplace_web.dto.requests.ProdutoRequest;
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
    private final Mapper mapper;
    private final MapperUpdate update;



    public List<ProdutoResponse> mostrarEstoque() {

        List<ProdutoResponse> produtos = mapper.listaDeProdutosResponse(repositorio.findAll());

        return produtos;
    }

    public void cadastrarProduto(ProdutosEntity produtos) {

        repositorio.saveAndFlush(produtos);
    }

    public void deletarProdutos(Long id) {

        repositorio.deleteById(id);
    }

    public ProdutoResponse mostrarProdutoPeloId(Long id) {

        ProdutoResponse produto = mapper.paraProdutoResponse(buscarProdutopeloId(id));
        return produto;
    }

    public void atualizarPreco(ProdutoRequest produto, Long id) {


        // buscando produto no banco
        ProdutosEntity produtoNoBanco = buscarProdutopeloId(id);

        // atualizadn preço
        ProdutosEntity produtoNoBancoAtualizado = update.atualizarProduto(produto, produtoNoBanco);

        repositorio.saveAndFlush(produtoNoBancoAtualizado);
    }


    public void atualizarEstoque (Long id, Integer quantidade){

        // buscando produto no banco
        ProdutosEntity produtoNoBanco = buscarProdutopeloId(id);

        // passando nova quantidade
        Integer novaQuantidade = produtoNoBanco.getQuantidade() + quantidade;

        // fazendo verificação de quantida para nao ser negativa
        verificarQuantidade(novaQuantidade);

        // passando a nova quantidade ao produto
        produtoNoBanco.setQuantidade(novaQuantidade);

        // salvando nova quantidade
        repositorio.saveAndFlush(produtoNoBanco);

    }

    // metodo para tratar quantidade que nao pode ser negativa
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

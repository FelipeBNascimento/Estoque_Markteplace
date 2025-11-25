package com.markteplace.bazan.markteplace_web.dto;

import com.markteplace.bazan.markteplace_web.dto.requests.ProdutoRequest;
import com.markteplace.bazan.markteplace_web.dto.responses.ProdutoResponse;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterProduto {

    public ProdutosEntity paraProdutoEntity(ProdutoRequest request){

        ProdutosEntity produto = ProdutosEntity.builder()

                .nome(request.nome())
                .quantidade(request.quantidade())
                .preco(request.preco())
                .build();

        return produto;
    }

    public ProdutoResponse paraProdutoResponse(ProdutosEntity entity){

        ProdutoResponse produto = new ProdutoResponse(
                entity.getId(), entity.getNome(), entity.getPreco(), entity.getQuantidade()
        );

        return produto;
    }

    public List<ProdutoResponse> listaProdutosResponses (List<ProdutosEntity> ListaProdutos){

        List<ProdutoResponse> produtos = new ArrayList<>();

        for(ProdutosEntity produto : ListaProdutos){

            produtos.add(paraProdutoResponse(produto));
        }

        return produtos;
    }
}

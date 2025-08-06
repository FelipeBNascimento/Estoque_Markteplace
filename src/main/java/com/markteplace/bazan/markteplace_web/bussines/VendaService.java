package com.markteplace.bazan.markteplace_web.bussines;

import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.VendasEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.repository.VendasRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VendaService {

    private final VendasRepository repositorio;
    private final ProdutosService produtosService;
    private final ProdutosEntity produtos;



    public List<VendasEntity> buscarTodasVendas(){

        return repositorio.findAll();
    }

    public VendasEntity realizarVendas(Long id, Integer quantidade){

        ProdutosEntity produto = produtosService.mostrarProduto(id);
        if (produto.getQuantidade() < quantidade){
            throw new RuntimeException("Estoque insuficiente para venda");
        }

        produtosService.alimentarEstoque(produto , id);

       VendasEntity novaVenda = new VendasEntity();
        novaVenda.setProdutos(produto);
        novaVenda.setQuantidade_vendida(quantidade);

        return repositorio.saveAndFlush(novaVenda);

    }

}

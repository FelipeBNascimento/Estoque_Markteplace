package com.markteplace.bazan.markteplace_web.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "itens_vendas")
public class ItensVendidosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="quantidade_vendida")
    private Integer quantidade_vendida;

    @Column (name="preco_vendido")
    private BigDecimal preco_vendido;

    @Column(name = "nome_produto")
    private String nome_produto;


    // isso aqui esta retornando mais nao precisava porque é somente o estoque vou arrumar depois
    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private ProdutosEntity produtos;

    // esse tbm não precisa retornar porque ai entra em um loop infinito
    @ManyToOne
    @JoinColumn (name = "venda_id")
    private VendaEntity venda;


}

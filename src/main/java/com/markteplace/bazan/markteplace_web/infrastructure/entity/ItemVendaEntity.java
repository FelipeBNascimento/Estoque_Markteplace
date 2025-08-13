package com.markteplace.bazan.markteplace_web.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "itens_vendas")
public class ItemVendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vendas;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private ProdutosEntity produtos;

    @ManyToOne
    @JoinColumn (name = " venda_id")
    private VendaEntity venda;


    @Column(name="quantidade_vendida")
    private Integer quantidade_vendida;

    @Column (name="preco_vendido")
    private Double preco_vendido;
}

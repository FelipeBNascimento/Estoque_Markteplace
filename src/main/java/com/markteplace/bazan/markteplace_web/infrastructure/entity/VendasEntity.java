package com.markteplace.bazan.markteplace_web.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "vendas")
public class VendasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vendas;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private ProdutosEntity produtos;

    @Column(name="quantidade_vendida")
    private Integer quantidade_vendida;

    @Column (name="preco_vendido")
    private Double preco_vendido;
}

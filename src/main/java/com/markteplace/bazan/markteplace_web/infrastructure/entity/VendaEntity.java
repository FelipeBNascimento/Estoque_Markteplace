package com.markteplace.bazan.markteplace_web.infrastructure.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table (name = "vendas_entity")
public class VendaEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVendaEntity> itensVenda = new ArrayList<>();

    @Column (name = "DataVenda")
    private LocalDateTime data;

}

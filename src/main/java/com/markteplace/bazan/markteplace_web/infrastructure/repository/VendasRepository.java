package com.markteplace.bazan.markteplace_web.infrastructure.repository;

import com.markteplace.bazan.markteplace_web.infrastructure.entity.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendasRepository extends JpaRepository<VendaEntity,Long> {
}

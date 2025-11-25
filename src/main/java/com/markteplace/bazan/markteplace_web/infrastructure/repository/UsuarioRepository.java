package com.markteplace.bazan.markteplace_web.infrastructure.repository;

import com.markteplace.bazan.markteplace_web.infrastructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    List<UsuarioEntity> findByAtivoTrue();
}

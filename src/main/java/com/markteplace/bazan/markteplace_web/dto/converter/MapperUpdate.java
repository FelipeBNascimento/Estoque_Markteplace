package com.markteplace.bazan.markteplace_web.dto.converter;

import com.markteplace.bazan.markteplace_web.dto.requests.UsuarioRequest;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MapperUpdate {

    UsuarioEntity atualizarEntity(UsuarioRequest request,
                                  @MappingTarget UsuarioEntity usuarioEntity);
}

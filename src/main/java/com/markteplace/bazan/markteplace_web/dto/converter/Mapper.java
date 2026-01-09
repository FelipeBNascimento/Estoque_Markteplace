package com.markteplace.bazan.markteplace_web.dto.converter;

import com.markteplace.bazan.markteplace_web.dto.requests.ProdutoRequest;
import com.markteplace.bazan.markteplace_web.dto.requests.UsuarioRequest;
import com.markteplace.bazan.markteplace_web.dto.responses.ProdutoResponse;
import com.markteplace.bazan.markteplace_web.dto.responses.UsuarioResponse;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.ProdutosEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.UsuarioEntity;
import org.mapstruct.Mapping;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    @Mapping(target = "id", ignore = true)
    ProdutosEntity paraProdutoEntity(ProdutoRequest produtoRequest);

    ProdutoResponse paraProdutoResponse (ProdutosEntity entity);

    List<ProdutoResponse> listaDeProdutosResponse(List<ProdutosEntity> entities);

    @Mapping(target = "id", ignore = true)
    UsuarioEntity paraUsuarioEntity (UsuarioRequest request);

    UsuarioResponse paraUsuarioResponse(UsuarioEntity entity);

    List<UsuarioResponse> listaUsuariosResponse(List<UsuarioEntity> entities);


}

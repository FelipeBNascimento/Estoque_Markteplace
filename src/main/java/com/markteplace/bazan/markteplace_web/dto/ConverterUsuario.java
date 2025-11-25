package com.markteplace.bazan.markteplace_web.dto;

import com.markteplace.bazan.markteplace_web.dto.requests.UsuarioRequest;
import com.markteplace.bazan.markteplace_web.dto.responses.UsuarioResponse;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterUsuario {

    public UsuarioEntity paraUsuarioEntity(UsuarioRequest request){

        UsuarioEntity usuario = UsuarioEntity.builder()

                .nome(request.nome())
                .email(request.email())
                .senha(request.senha())
                .saldo(request.saldo())
                .build();

        return usuario;
    }

    public UsuarioResponse paraResponse(UsuarioEntity entity){

        UsuarioResponse usuarioResponse = new UsuarioResponse(entity.getId(),
                entity.getNome(), entity.getEmail(), entity.getSaldo(),entity.isAtivo());

        return usuarioResponse;
    }

    public List<UsuarioResponse> paraListaResponse(List<UsuarioEntity> entities){


        List<UsuarioResponse> lista = new ArrayList<>();

        for(UsuarioEntity usuario : entities){

            lista.add(paraResponse(usuario));
        }

        return lista;
    }
}

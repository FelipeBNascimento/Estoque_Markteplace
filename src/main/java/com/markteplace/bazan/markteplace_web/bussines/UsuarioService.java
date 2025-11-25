package com.markteplace.bazan.markteplace_web.bussines;

import com.markteplace.bazan.markteplace_web.dto.ConverterUsuario;
import com.markteplace.bazan.markteplace_web.dto.requests.UsuarioRequest;
import com.markteplace.bazan.markteplace_web.dto.responses.UsuarioResponse;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.UsuarioEntity;
import com.markteplace.bazan.markteplace_web.infrastructure.exceptions.IdNaoEncontrado;
import com.markteplace.bazan.markteplace_web.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final ConverterUsuario converterUsuario;

    public UsuarioResponse criarUsuario(UsuarioRequest request){

        UsuarioEntity entity = converterUsuario.paraUsuarioEntity(request);
        entity.setAtivo(true);
        UsuarioEntity usuariosalvo =  repository.save(entity);
        UsuarioResponse response = converterUsuario.paraResponse(usuariosalvo);
        return response;
    }

    public UsuarioResponse visualizarUsuario(Long id){

        UsuarioEntity usuarioBanco = buscarUsuario(id);

        UsuarioResponse usuario = converterUsuario.paraResponse(usuarioBanco);

        return usuario;
    }

    public List<UsuarioResponse> usuariosAtivos(){

        List<UsuarioResponse> lista = converterUsuario.paraListaResponse(repository.findByAtivoTrue());

        return lista;
    }

    public void deletarUsuario(Long id){

        UsuarioEntity usuarioBanco = buscarUsuario(id);
        usuarioBanco.setAtivo(false);
        repository.save(usuarioBanco);
    }

    public UsuarioResponse atualizarUsuario(UsuarioRequest usuarioRequest, Long id){

        UsuarioEntity usuariobanco = buscarUsuario(id);

        UsuarioEntity usuarioAtualizado = UsuarioEntity.builder()

                .id(usuariobanco.getId())
                .nome(usuarioRequest.nome()!= null ? usuarioRequest.nome() : usuariobanco.getNome())
                .email(usuarioRequest.email()!= null ? usuarioRequest.email() : usuariobanco.getEmail())
                .saldo(usuarioRequest.saldo() != null ? usuarioRequest.saldo(): usuariobanco.getSaldo())
                .senha(usuarioRequest.senha()!= null? usuarioRequest.senha() : usuariobanco.getSenha())
                .ativo(true)
                .build();

        UsuarioEntity usuarioSalvo =  repository.save(usuarioAtualizado);
        UsuarioResponse usuarioResponse = converterUsuario.paraResponse(usuarioSalvo);
        return usuarioResponse;

    }


    // metodo criado somente para buscar usuario e utilizar em outros metodos visualizar delete e atualizar
    public UsuarioEntity buscarUsuario(Long id){

        UsuarioEntity usuarioBanco = repository.findById(id).orElseThrow(
                ()-> new IdNaoEncontrado("Id não encontrado")
        );

        return usuarioBanco;

    }

}

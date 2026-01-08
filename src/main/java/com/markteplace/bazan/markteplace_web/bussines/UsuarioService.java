package com.markteplace.bazan.markteplace_web.bussines;

import com.markteplace.bazan.markteplace_web.dto.ConverterUsuario;
import com.markteplace.bazan.markteplace_web.dto.converter.Mapper;
import com.markteplace.bazan.markteplace_web.dto.converter.MapperUpdate;
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
    private final Mapper mapper;
    private final MapperUpdate update;

    public UsuarioResponse criarUsuario(UsuarioRequest request){

        UsuarioEntity entity = mapper.paraUsuarioEntity(request);
        entity.setAtivo(true);
        UsuarioEntity usuariosalvo =  repository.save(entity);
        UsuarioResponse response = mapper.paraUsuarioResponse(usuariosalvo);
        return response;
    }

    public UsuarioResponse visualizarUsuario(Long id){

        UsuarioEntity usuarioBanco = buscarUsuario(id);

        UsuarioResponse usuario = mapper.paraUsuarioResponse(usuarioBanco);

        return usuario;
    }

    public List<UsuarioResponse> usuariosAtivos(){

        List<UsuarioResponse> lista = mapper.listaUsuariosResponse(repository.findByAtivoTrue());

        return lista;
    }

    public void deletarUsuario(Long id){

        UsuarioEntity usuarioBanco = buscarUsuario(id);
        usuarioBanco.setAtivo(false);
        repository.save(usuarioBanco);
    }

    public UsuarioResponse atualizarUsuario(UsuarioRequest usuarioRequest, Long id){

        UsuarioEntity usuariobanco = buscarUsuario(id);

        UsuarioEntity usuarioAtualizado = update.atualizarEntity(usuarioRequest, usuariobanco);

        UsuarioEntity usuarioSalvo =  repository.save(usuarioAtualizado);
        UsuarioResponse usuarioResponse = mapper.paraUsuarioResponse(usuarioSalvo);
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

package com.markteplace.bazan.markteplace_web.controller;

import com.markteplace.bazan.markteplace_web.bussines.UsuarioService;
import com.markteplace.bazan.markteplace_web.dto.requests.UsuarioRequest;
import com.markteplace.bazan.markteplace_web.dto.responses.UsuarioResponse;
import com.markteplace.bazan.markteplace_web.infrastructure.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;


    @PostMapping
    public ResponseEntity<UsuarioResponse> criarusuario(@RequestBody UsuarioRequest request) {

        return ResponseEntity.ok(service.criarUsuario(request));
    }

    @GetMapping
    public ResponseEntity<UsuarioResponse> visualizarusuarioPeloId(@RequestParam Long id) {

        return ResponseEntity.ok(service.visualizarUsuario(id));
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<UsuarioResponse>> buscarUsuariosAtivos(){

        return ResponseEntity.ok(service.usuariosAtivos());
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarPeloId(@RequestParam Long id) {

        service.deletarUsuario(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@RequestBody UsuarioRequest request,
                                                            @RequestParam Long id) {

        return ResponseEntity.ok(service.atualizarUsuario(request, id));
    }

}

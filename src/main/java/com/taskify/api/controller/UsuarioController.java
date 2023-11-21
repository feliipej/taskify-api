package com.taskify.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskify.api.model.Usuario;
import com.taskify.api.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listaUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Usuario>> obterUsuario(@PathVariable("id") Long idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findById(idUsuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable("id") Long idUsuario, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);

        if (usuarioExistente.isPresent()) { // esse metodo torna um boleano se o usuário existe
            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/email")
    public Optional<Usuario> buscarUsuarioPeloEmail(@RequestParam ("email") String email) {
        return usuarioRepository.findByEmail(email);
    }
}

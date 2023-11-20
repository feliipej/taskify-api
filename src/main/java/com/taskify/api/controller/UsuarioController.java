package com.taskify.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskify.api.model.Usuario;
import com.taskify.api.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping
    public List<Usuario> listaUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Usuario> obterUsuario(@PathVariable("id") Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable("id") Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);

        return "Usuário deletado com sucesso";
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable("id") Long idUsuario, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);

        if (usuarioExistente.isPresent()) { // esse metodo torna um boleano se esá vazio ou não
            return usuarioRepository.save(usuario);
        }

        return null;
    }
}

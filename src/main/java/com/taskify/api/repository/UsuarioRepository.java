package com.taskify.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskify.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}

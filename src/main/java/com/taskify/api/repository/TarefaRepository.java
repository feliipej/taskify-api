package com.taskify.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.taskify.api.model.Tarefa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query("SELECT t FROM tb_tarefas t WHERE t.projeto.idProjeto = :idProjeto")
    Optional<List<Tarefa>> findByProjeto(@Param("idProjeto") Long idProjeto);

    @Query("SELECT t FROM tb_tarefas t WHERE t.projeto.idProjeto = :idUsuario")
    Optional<Page<Tarefa>> findByProjeto(Pageable paginacao, @Param("idUsuario") Long idProjeto);

    @Query("SELECT t FROM tb_tarefas t WHERE t.usuario.idUsuario = :idUsuario")
    Optional<List<Tarefa>> findByUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT t FROM tb_tarefas t WHERE t.usuario.idUsuario = :idUsuario")
    Optional<Page<Tarefa>> findByUsuario(Pageable paginacao, @Param("idUsuario") Long idUsuario);

    @Query("SELECT t FROM tb_tarefas t WHERE t.projeto.idProjeto = :idProjeto AND t.usuario.idUsuario = :idUsuario")
    Optional<List<Tarefa>> findByProjetoUsuario(@Param("idProjeto") Long idProjeto, @Param("idUsuario") Long idUsuario);
}

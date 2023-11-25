package com.taskify.api.controller;


import com.taskify.api.model.Tarefa;
import com.taskify.api.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    // CRUD
    @PostMapping
    public ResponseEntity<Tarefa> cadastrarTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaRepository.save(tarefa));
    }

    @GetMapping
    public ResponseEntity<Page<Tarefa>> listaTarefa(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.findAll(paginacao));
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Tarefa>> obterTarefa(@PathVariable("id") Long idTarefa) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.findById(idTarefa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTarefa(@PathVariable("id") Long idTarefa) {
        tarefaRepository.deleteById(idTarefa);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable("id") Long idTarefa, @RequestBody Tarefa tarefa) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(idTarefa);

        if (tarefaExistente.isPresent()) { // metodo retorna um boleano, se o usuário existe
            return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.save(tarefa));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("projeto/{idProjeto}")
    public ResponseEntity<Optional<List<Tarefa>>> buscarTarefasDeUmProjeto(@PathVariable("idProjeto") Long idProjeto) {
        return ResponseEntity.ok().body(tarefaRepository.findByProjeto(idProjeto));

    }

    @GetMapping("usuarios/{idUsurario}")
    public ResponseEntity<Optional<List<Tarefa>>> buscarTarefasDeUmUsuario(@PathVariable("idUsuario") Long idUsuario) {
        return ResponseEntity.ok().body(tarefaRepository.findByProjeto(idUsuario));
    }

    @GetMapping("projeto/{idProjeto}/usuarios/{idUsurario}")
    public ResponseEntity<Optional<List<Tarefa>>> buscarTarefasPorProjetoEUsuario(@PathVariable("idProjeto") Long idProjeto, @PathVariable("idUsuario") Long idUsuario) {
        return ResponseEntity.ok().body(tarefaRepository.findByProjetoUsuario(idProjeto, idUsuario));
    }
}

package com.taskify.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskify.api.constants.Prioridade;
import com.taskify.api.constants.Situacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_tarefas")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarefa;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT") // PASSANDO O PARÂMETRO PARA MUDAR DE VARCHAR PARA TEXT NO BD
    private String descricao;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeConclusao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @ManyToOne(optional = true) // annonation que indica que o usuário pode ter várias tarefas N:1, o atributo é opcional
    @JoinColumn(name = "idUsuario") // Chave estrangeira
    private Usuario usuario;

    @ManyToOne
    @JoinColumn( name = "idProjeto")
    private Projeto projeto;
}

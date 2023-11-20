package com.taskify.api.model;

import com.taskify.api.constants.Genero;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Annotation para criar Getters/Setters
@NoArgsConstructor // Annotation de Construtor padrão
@AllArgsConstructor // Annotation do Construtor com Parâmetros
@Entity(name = "tb_usuarios") // Annotation que indica que essa classe será uma tabela no banco de dados
public class Usuario {
    
    @Id // Annotation que indica a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotation que irá gerar automáticamente um novo usuario, a ser criado
    private Long idUsuario;

    @Column(nullable = false) // Annotation que indica que o atributo é obrigatório
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Embedded
    private Endereco endereco;
}

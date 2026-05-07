package com.exemplo.model;

import jakarta.persistence.*;

@Entity
@Table(name="alunos_tb")
public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	
	private Double nota;

	public Aluno() {
	
	}

	public Aluno(Long id, String nome, Double nota) {
		this.id = id;
		this.nome = nome;
		this.nota = nota;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", nota=" + nota + "]";
	}
	
}

package com.exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}

package com.exemplo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.model.Aluno;
import com.exemplo.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	public List<Aluno> listarTodos(){
		return repository.findAll(); 
	}
	
	public Aluno salvar(Aluno aluno) {
		return repository.save(aluno);
	}
	
	public void deletar(Long id) {
		repository.deleteById(id);
	}
}

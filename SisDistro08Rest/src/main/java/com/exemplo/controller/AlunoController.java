package com.exemplo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.model.Aluno;
import com.exemplo.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoService service;
	
	@GetMapping
	public List<Aluno> listar(){
		return service.listarTodos();
	}
	
	@PostMapping
	public Aluno salvar(@RequestBody Aluno aluno) {
		return service.salvar(aluno);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
	}
}

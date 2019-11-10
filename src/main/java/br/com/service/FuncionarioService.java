package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Funcionario;
import br.com.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired private FuncionarioRepository repository;

	public List<Funcionario> list() {
		return repository.findAll();
	}

	public void save(Funcionario funcionario) {
		repository.save(funcionario);
	}

	public Funcionario getById(Long id) {
		return repository.findOne(id);
	}

	public void remove(Long id) {
		repository.delete(id);
	}
	
}

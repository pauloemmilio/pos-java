package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Dependente;
import br.com.repository.DependenteRepository;

@Service
public class DependenteService {

	@Autowired private DependenteRepository repository;

	public List<Dependente> list() {
		return repository.findAll();
	}

	public void save(Dependente dependente) {
		repository.save(dependente);
	}

	public Dependente getById(Long id) {
		return repository.findOne(id);
	}

	public void remove(Long id) {
		repository.delete(id);
	}
	
}

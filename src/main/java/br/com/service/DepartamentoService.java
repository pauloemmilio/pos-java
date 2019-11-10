package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.model.Departamento;
import br.com.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired private DepartamentoRepository repository;

	public List<Departamento> list() {
		return repository.findAll();
	}

	public void save(Departamento departamento) {
		repository.save(departamento);
	}

	public Departamento getById(Long id) {
		return repository.findOne(id);
	}

	public void remove(Long id) {
		repository.delete(id);
	}
}

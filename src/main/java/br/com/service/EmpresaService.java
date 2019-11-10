package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Empresa;
import br.com.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;

	@Transactional(readOnly=true)
	public List<Empresa> list() {
		return repository.findAll();
	}

	@Transactional
	public Empresa save(Empresa empresa) {
		return repository.save(empresa);
	}

	@Transactional(readOnly=true)
	public Empresa getById(Long id) {
		return this.repository.findOne(id);
	}

	@Transactional
	public void remove(Long id) {
		this.repository.delete(id);
	}

	@Transactional
	public boolean ativarDesativar(Long id) {
		boolean ativou = false;

		Empresa empresa = this.repository.getOne(id);
		if (empresa.isAtivo()) {
			empresa.setAtivo(false);
			return ativou;
		} else {
			empresa.setAtivo(true);
			ativou = true;
		}
		return ativou;
	}
}

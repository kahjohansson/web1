package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;

@Service
@Transactional(readOnly = false)
public class ProfissionalService implements IProfissionalService {

	@Autowired
	ProfissionalDAO dao;
	
	public void salvar(Profissional Profissional) {
		dao.save(Profissional);
	}

	public void excluir(String cpf) {
		dao.deleteById(cpf);
	}

	@Transactional(readOnly = true)
	public Profissional buscarPorCpf(String cpf) {
		return dao.findByCpf(cpf);
	}

	@Transactional(readOnly = true)
	public Profissional buscarPorEmail(String email) {
		return dao.findByEmail(email);
	}

	@Transactional(readOnly = true)
	public List<Profissional> buscarPorEspecialidade(String especialidade) {
		return dao.findByEspecialidade(especialidade);
	}

	@Transactional(readOnly = true)
	public List<Profissional> buscarTodos() {
		return dao.findAll();
	}

}

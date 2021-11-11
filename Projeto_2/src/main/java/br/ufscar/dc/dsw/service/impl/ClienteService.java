package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@Service
@Transactional(readOnly = false)
public class ClienteService implements IClienteService {

	@Autowired
	ClienteDAO dao;
	
	public void salvar(Cliente cliente) {
		dao.save(cliente);
	}

	public void excluir(String cpf) {
		dao.deleteById(cpf);
	}

	@Transactional(readOnly = true)
	public Cliente buscarPorCpf(String cpf) {
		return dao.findByCpf(cpf);
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public Cliente buscarPorEmail(String email) {
		return dao.findByEmail(email);
	}

}

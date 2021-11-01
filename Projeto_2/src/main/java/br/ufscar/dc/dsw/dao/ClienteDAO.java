package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Cliente;

@SuppressWarnings("unchecked")
public interface ClienteDAO extends CrudRepository<Cliente, String>{

	Cliente findByCpf(String cpf);

	List<Cliente> findAll();
	
	Cliente save(Cliente cliente);

	void deleteById(String cpf);
}
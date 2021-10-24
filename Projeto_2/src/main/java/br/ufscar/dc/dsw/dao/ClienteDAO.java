package br.ufscar.dc.dsw.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Cliente;

@SuppressWarnings("unchecked")
public interface ClienteDAO extends CrudRepository<Cliente, String>{

	Cliente findByCPF(String cpf);

	List<Cliente> findAll();
	
	Cliente save(Cliente cliente);

	void deleteById(String cpf);
}
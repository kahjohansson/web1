package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;

public interface IClienteService {

	Cliente buscarPorCpf(String cpf);

	Cliente buscarPorEmail(String email);
	
	List<Cliente> buscarTodos();
	
	void salvar(Cliente cliente);
	
	void excluir(String cpf);
	
}

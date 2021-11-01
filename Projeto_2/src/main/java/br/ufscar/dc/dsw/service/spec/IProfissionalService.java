package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public interface IProfissionalService {

	Profissional buscarPorCpf(String cpf);
	
	List<Profissional> buscarTodos();
	
	void salvar(Profissional Profissional);
	
	void excluir(String cpf);
	
}
package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Profissional;

public interface IProfissionalService {

	Profissional buscarPorCpf(String cpf);

	Profissional buscarPorEmail(String email);

	List<Profissional> buscarPorEspecialidade(String especialidade);
	
	List<Profissional> buscarTodos();
	
	void salvar(Profissional Profissional);
	
	void excluir(String cpf);
	
}
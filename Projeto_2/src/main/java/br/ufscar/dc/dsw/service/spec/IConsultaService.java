package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;

public interface IConsultaService {

	Consulta buscarPorId(Long id);

	List<Consulta> buscarTodos();

	List<Consulta> buscarPorCpfCliente(String cpfCliente);

	List<Consulta> buscarPorCpfProfissional(String cpfProfissional);

	void salvar(Consulta consulta);

	void excluir(Long id);
}

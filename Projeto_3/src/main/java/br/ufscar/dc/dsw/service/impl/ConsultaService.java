package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.service.spec.IConsultaService;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {
    
    @Autowired
	ConsultaDAO dao;
	
	public void salvar(Consulta consulta) {
		dao.save(consulta);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Consulta buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarPorCpfCliente(String cpfCliente) {
		return dao.findByCpfCliente(cpfCliente);
	}

	@Transactional(readOnly = true)
	public List<Consulta> buscarPorCpfProfissional(String cpfProfissional) {
		return dao.findByCpfProfissional(cpfProfissional);
	}
}

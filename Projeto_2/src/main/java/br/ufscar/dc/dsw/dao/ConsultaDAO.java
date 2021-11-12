package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface ConsultaDAO extends CrudRepository<Consulta, Long> {

    Consulta findById(long id);
	
	List<Consulta> findAll();
	
    // List<Consulta> findByCliente(Cliente cliente);

    List<Consulta> findByCpfCliente(String cpfCliente);
	
    // List<Consulta> findByProfissional(Profissional profissional);

    List<Consulta> findByCpfProfissional(String cpfProfissional);
    
    Consulta save(Consulta consulta);

	void deleteById(Long id);
    
}

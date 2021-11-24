package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Profissional;

@SuppressWarnings("unchecked")
public interface ProfissionalDAO extends CrudRepository<Profissional, String>{

	Profissional findByCpf(String cpf);

	Profissional findByEmail(String email);

	List<Profissional> findByEspecialidade(String especialidade);

	List<Profissional> findAll();
	
	Profissional save(Profissional Profissional);

	void deleteById(String cpf);
    
}

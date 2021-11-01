package br.ufscar.dc.dsw.dao;

import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.UsuarioDAO;

@SuppressWarnings("unchecked")
public interface UsuarioDAO extends CrudRepository<Usuario, String>{

    Usuario findByEmail(String email);
}

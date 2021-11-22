package br.ufscar.dc.dsw.dao;

import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.Usuario;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@SuppressWarnings("unchecked")
public interface UsuarioDAO extends CrudRepository<Usuario, String>{

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario getUserByEmail(@Param("email") String email);
    
    Usuario findByEmail(String email);

    
}

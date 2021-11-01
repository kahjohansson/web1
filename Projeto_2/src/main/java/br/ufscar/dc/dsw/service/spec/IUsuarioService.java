package br.ufscar.dc.dsw.service.spec;

import br.ufscar.dc.dsw.domain.Usuario;

public interface IUsuarioService {

	Usuario buscarPorEmail(String email);
}

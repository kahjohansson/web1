package br.ufscar.dc.dsw.domain;

import java.util.Set;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import br.ufscar.dc.dsw.domain.Usuario;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {

	public Cliente() {
		super();
	}

	public Cliente(String cpf, String nome, String email, String senha, String telefone, String sexo, String dataNascimento) {
		super(cpf, nome, email, senha);
		this.telefone = telefone;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
	}

	private String telefone;
	private String sexo;
	private String dataNascimento;

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}

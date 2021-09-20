package br.ufscar.dc.dsw.POJO;


public class Cliente extends Usuario {

	public Cliente(String cpf, String nome, String email, String senha, String telefone, String sexo, String dataNasc) {
		super(cpf, nome, email, senha);
		this.telefone = telefone;
		this.sexo = sexo;
		this.dataNasc = dataNasc;
	}

	private String telefone;
	private String sexo;
	private String dataNasc;

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

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
}

package br.ufscar.dc.dsw.domain;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario{

    @Id
    private String cpf;

    @Column(nullable = false, unique = true, length = 256)
	private String nome;

    @Column(nullable = false, unique = true, length = 256)
	private String email;

    @Column(nullable = false, unique = false, length = 256)
	private String senha;

    @Column(nullable = false, unique = false, length = 64)
	private String papel;

    public Usuario(String cpf, String nome, String email, String senha, String papel) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
    }

    public Usuario() {
        
    }
    
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

}

package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.impl.ClienteService;
import br.ufscar.dc.dsw.service.impl.UsuarioService;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@SpringBootApplication
public class Projeto2Application {

	public static void main(String[] args) {
		SpringApplication.run(Projeto2Application.class, args);

	}

	// @Bean
	// public CommandLineRunner demo(ClienteDAO clienteDAO) {
	// 	return (args) -> {

	// 	// testando o DAO de cliente
	// 	String cpf = "89999999999";
	// 	String nome = "teste";
	// 	String email = "testando@gmail.com";
	// 	String senha = "senha";
	// 	String papel = "cliente";
	// 	String telefone = "11912345678";
	// 	String sexo = "feminino";
	// 	String dataNascimento = "1999-01-01";
	// 	Cliente cliente = new Cliente(cpf, nome, email, senha, papel, telefone, sexo, dataNascimento);
	// 	clienteDAO.save(cliente);
		
	// 	};
	// }

}

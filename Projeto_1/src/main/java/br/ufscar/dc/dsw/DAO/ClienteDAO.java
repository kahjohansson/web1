package br.ufscar.dc.dsw.DAO;

import br.ufscar.dc.dsw.POJO.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
	
	public ClienteDAO() {
		
	}
	
	public void insere(Cliente cliente) {

        String sql = "INSERT INTO Cliente (cpf, nome, email, senha, telefone, sexo, dataNasc) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
        	Class.forName("org.apache.derby.jdbc.ClientDriver");
			String url = "jdbc:derby://localhost:1527/sistema_agendamento";
			Connection conn = (Connection) DriverManager.getConnection(url, "root", "root");
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getCpf());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getSenha());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setDate(7, java.sql.Date.valueOf(cliente.getDataNasc()));
            statement.executeUpdate();

            statement.close();
            conn.close();
            
        } catch (SQLException e) {
        	System.out.println("O comando SQL não pode ser executado!");
        } catch (ClassNotFoundException e) {
        	System.out.println("A classe do driver de conexão não foi encontrada!");
		}
    }
	
}

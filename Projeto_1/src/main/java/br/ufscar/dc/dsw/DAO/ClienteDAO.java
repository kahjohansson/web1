package br.ufscar.dc.dsw.DAO;

import br.ufscar.dc.dsw.POJO.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class ClienteDAO extends DAO {
	
	public ClienteDAO() {
		
	}
	
	public void insert(Cliente cliente) {

        String query_1 = "INSERT INTO usuarios (cpf, nome, email, senha) VALUES (?, ?, ?, ?)";
        String query_2 = "INSERT INTO clientes (cpf, sexo, telefone, data_nascimento) VALUES (?, ?, ?, ?)";

        try {
			Connection conn = this.getConnection();

            conn.setAutoCommit(false); // ativa modo de transação
            
            PreparedStatement statement_1 = conn.prepareStatement(query_1);
            statement_1 = conn.prepareStatement(query_1);
            statement_1.setString(1, cliente.getCpf());
            statement_1.setString(2, cliente.getNome());
            statement_1.setString(3, cliente.getEmail());
            statement_1.setString(4, cliente.getSenha());
            statement_1.executeUpdate();
            statement_1.close();

            PreparedStatement statement_2 = conn.prepareStatement(query_2);
            statement_2 = conn.prepareStatement(query_2);
            statement_2.setString(1, cliente.getCpf());
            statement_2.setString(2, cliente.getSexo());
            statement_2.setString(3, cliente.getTelefone());
            statement_2.setDate(4, Date.valueOf(cliente.getDataNascimento()));
            statement_2.executeUpdate();
            statement_2.close();

            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
            
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
    }

    public List<Cliente> selectAll() {

        List<Cliente> listaClientes = new ArrayList<>();

        String query = "SELECT * FROM clientes INNER JOIN usuarios ON clientes.cpf = usuarios.cpf";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                
            	String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String sexo = resultSet.getString("sexo");
                String telefone = resultSet.getString("telefone");
                String data_nascimento = resultSet.getDate("data_nascimento").toString();
         
                Cliente cliente = new Cliente(cpf, nome, email, senha, telefone, sexo, data_nascimento);
                
                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }
	
    public Cliente selectByCpf(String cpf) {

        Cliente cliente = null;

        String sql = "SELECT * FROM clientes WHERE cpf = ? INNER JOIN usuarios ON clientes.cpf = usuarios.cpf";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String sexo = resultSet.getString("sexo");
                String telefone = resultSet.getString("telefone");
                String data_nascimento = resultSet.getDate("data_nascimento").toString();

                cliente = new Cliente(cpf, nome, email, senha, telefone, sexo, data_nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public void update(Cliente cliente) {

        String query_1 = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE cpf = ?";
        String query_2 = "UPDATE clientes SET telefone = ?, sexo = ?, dataNasc = ? WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement_1 = conn.prepareStatement(query_1);
            PreparedStatement statement_2 = conn.prepareStatement(query_2);

            conn.setAutoCommit(false); // ativa modo de transação

            statement_1.setString(1, cliente.getTelefone());
            statement_1.setString(2, cliente.getSexo());
            statement_1.setDate(3, java.sql.Date.valueOf(cliente.getDataNascimento()));
            statement_1.setString(4, cliente.getCpf());
            statement_1.executeUpdate();
            statement_1.close();
         
            statement_2.setString(1, cliente.getNome());
            statement_2.setString(2, cliente.getEmail());
            statement_2.setString(3, cliente.getSenha());
            statement_2.setString(4, cliente.getCpf());
            statement_2.executeUpdate();
            statement_2.close();
            
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Cliente cliente) {

        String query_1 = "DELETE FROM usuarios WHERE cpf = ?";
    	String query_2 = "DELETE FROM clientes WHERE cpf = ?";
        
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement_1 = conn.prepareStatement(query_1);
            PreparedStatement statement_2 = conn.prepareStatement(query_2);

            conn.setAutoCommit(false); // ativa modo de transação

            statement_1.setString(1, cliente.getCpf());
            statement_1.executeUpdate();
            statement_1.close();

            statement_2.setString(1, cliente.getCpf());
            statement_2.executeUpdate();
            statement_2.close();
            
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

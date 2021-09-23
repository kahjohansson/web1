package br.ufscar.dc.dsw.DAO;

import br.ufscar.dc.dsw.POJO.Profissional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class ProfissionalDAO extends DAO {
	
	public ProfissionalDAO() {
		
	}
	
	public void insert(Profissional profissional) {

        String query_1 = "INSERT INTO usuarios (cpf, nome, email, senha) VALUES (?, ?, ?, ?)";
        String query_2 = "INSERT INTO profissionais (cpf, area, especialidade, curriculo) VALUES (?, ?, ?, ?)";

        try {
			Connection conn = this.getConnection();

            conn.setAutoCommit(false); // ativa modo de transação
            
            PreparedStatement statement_1 = conn.prepareStatement(query_1);
            statement_1 = conn.prepareStatement(query_1);
            statement_1.setString(1, profissional.getCpf());
            statement_1.setString(2, profissional.getNome());
            statement_1.setString(3, profissional.getEmail());
            statement_1.setString(4, profissional.getSenha());
            statement_1.executeUpdate();
            statement_1.close();

            PreparedStatement statement_2 = conn.prepareStatement(query_2);
            statement_2 = conn.prepareStatement(query_2);
            statement_2.setString(1, profissional.getCpf());
            statement_2.setString(2, profissional.getArea());
            statement_2.setString(3, profissional.getEspecialidade());
            statement_2.setString(4, profissional.getCurriculo());
            statement_2.executeUpdate();
            statement_2.close();

            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
            
        } catch (SQLException e) {
        	throw new RuntimeException(e);
        }
    }

    public List<Profissional> selectAll() {

        List<Profissional> listaProfissional = new ArrayList<>();

        String query = "SELECT * FROM profissionais INNER JOIN usuarios ON profissionais.cpf = usuarios.cpf";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                
            	String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String area = resultSet.getString("area");
                String especialidade = resultSet.getString("especialidade");
                String curriculo = resultSet.getString("curriculo");
                Profissional profissional = new Profissional(cpf, nome, email, senha, area,especialidade, curriculo);
                listaProfissional.add(profissional);
                
                listaProfissional.add(profissional);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProfissional;
    }
	
    public Profissional selectByCpf(String cpf) {

        Profissional profissional = null;

        String sql = "SELECT * FROM profissionais INNER JOIN usuarios ON profissionais.cpf = usuarios.cpf WHERE usuarios.cpf = ? ";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String area = resultSet.getString("area");
                String especialidade = resultSet.getString("especialidade");
                String curriculo = resultSet.getString("curriculo");

                profissional = new Profissional(cpf, nome, email, senha, area,especialidade, curriculo);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profissional;
    }

    public void update(Profissional profissional) {

        String query_1 = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE cpf = ?";
        String query_2 = "UPDATE profissionais SET area = ?, especialidade = ?, curriculo = ? WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement_1 = conn.prepareStatement(query_1);
            PreparedStatement statement_2 = conn.prepareStatement(query_2);

            conn.setAutoCommit(false); // ativa modo de transação

            statement_1.setString(1, profissional.getNome());
            statement_1.setString(2, profissional.getEmail());
            statement_1.setString(3, profissional.getSenha());
            statement_1.setString(4, profissional.getCpf());
            statement_1.executeUpdate();
            statement_1.close();

            statement_2.setString(1, profissional.getArea());
            statement_2.setString(2, profissional.getEspecialidade());
            statement_2.setString(3, profissional.getCurriculo());
            statement_2.setString(4, profissional.getCpf());
            statement_2.executeUpdate();
            statement_2.close();
            
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Profissional profissional) {

        String query_1 = "DELETE FROM profissionais WHERE cpf = ?";
        String query_2 = "DELETE FROM usuarios WHERE cpf = ?";
    	
        
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement_1 = conn.prepareStatement(query_1);
            PreparedStatement statement_2 = conn.prepareStatement(query_2);

            conn.setAutoCommit(false); // ativa modo de transação

            statement_1.setString(1, profissional.getCpf());
            statement_1.executeUpdate();
            statement_1.close();

            statement_2.setString(1, profissional.getCpf());
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

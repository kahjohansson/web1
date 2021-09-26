package br.ufscar.dc.dsw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufscar.dc.dsw.POJO.Administrador;

public class AdministradorDAO extends DAO {

    public AdministradorDAO() {

    }

    public Administrador selectByCpf(String cpf) {

        Administrador administrador = null;

        String sql = "SELECT * FROM usuarios WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");

                administrador = new Administrador(cpf, nome, email, senha);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return administrador;
    }
    
}

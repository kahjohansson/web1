package br.ufscar.dc.dsw.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class UsuarioDAO extends DAO {
    
    public UsuarioDAO() {

    }

    public String selectByEmail(String email) {

        String cpf = null;

        String sql = "SELECT * FROM usuarios WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cpf = resultSet.getString("cpf");
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cpf;
    }

}

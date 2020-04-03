package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO {

    public void doSave(Customer customer) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into cliente (username, nome, cognome, email, passwordUtente) " +
                            "values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, customer.getUsername());
            ps.setString(2, customer.getNome());
            ps.setString(3, customer.getCognome());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getPassword());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("insert error.");

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            customer.setId(id);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}

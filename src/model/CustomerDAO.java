package model;

import java.util.*;
import java.sql.*;

public class CustomerDAO {

    // Rimuove un customer dal database
    public void doRemoveCustomer(Customer c) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("delete from cliente where id = ?");

            ps.setInt(1, c.getId());
            ps.execute();

        } catch(SQLException s) {

            throw new RuntimeException(s);
        }
    }

    // Preleva tutti i customer
    public List<Customer> doRetrieveAllCustomers() {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select * from cliente");
            ResultSet rs = ps.executeQuery();

            Customer cu;
            List<Customer> clienti = new ArrayList<>();

            while (rs.next()) {

                if (!rs.getBoolean("amministratore")) {

                    cu = new Customer();
                    cu.setId(rs.getInt(1));
                    cu.setUsername(rs.getString(2));
                    cu.setNome(rs.getString(3));
                    cu.setCognome(rs.getString(4));
                    cu.setEmail(rs.getString(5));
                    cu.setPassword(rs.getString(6));

                    clienti.add(cu);
                }
            }

            return clienti;

        } catch(SQLException s) {

            throw new RuntimeException(s);
        }

    }

    /* Registra un cliente */
    public void doRegisterCustomer(Customer c) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "insert into cliente (username, nome, cognome, email, passwordUtente, amministratore) " +
                            "values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, c.getUsername());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getCognome());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getPassword());
            ps.setBoolean(6, c.isAmministratore());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("insert error.");

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            c.setId(id);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    /* Returna un cliente con username a password */
    public Customer doRetrieveByUsernamePassword(String username, String password) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select id, username, nome, cognome, email, passwordUtente, amministratore " +
                    "from cliente where username = ? and passwordUtente = ?");

            Customer c = new Customer();
            c.setPassword(password);

            ps.setString(1, username);
            ps.setString(2, c.getPassword());

            ResultSet rs = ps.executeQuery();

            Customer cu = null;

            while (rs.next()) {

                cu = new Customer();

                cu.setId(rs.getInt(1));
                cu.setUsername(rs.getString(2));
                cu.setNome(rs.getString(3));
                cu.setCognome(rs.getString(4));
                cu.setEmail(rs.getString(5));
                cu.setPassword(rs.getString(6));
                cu.setAmministratore(rs.getBoolean(7));
            }

            return cu;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    // Preleva il customer con lo username
    public Customer doRetrieveByUsername(String username) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, nome, cognome, email, passwordUtente, amministratore FROM cliente WHERE username=?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer p = new Customer();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setNome(rs.getString(3));
                p.setCognome(rs.getString(4));
                p.setEmail(rs.getString(5));
                p.setPassword(rs.getString(6));
                p.setAmministratore(rs.getBoolean(7));
                return p;
            }

            return null;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    // Preleva il customer con un certo id
    public Customer doRetrieveById(int id) {

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id, username, nome, cognome, email, passwordUtente, amministratore FROM cliente WHERE id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer p = new Customer();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString(2));
                p.setNome(rs.getString(3));
                p.setCognome(rs.getString(4));
                p.setEmail(rs.getString(5));
                p.setPassword(rs.getString(6));
                p.setAmministratore(rs.getBoolean(7));
                return p;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Aggiorna i dati del customer
    public void doUpdateDati(Customer c){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "update cliente set username=?, nome=?, cognome=?, email=? where id=?",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, c.getUsername());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getCognome());
            ps.setString(4, c.getEmail());
            ps.setInt(5,c.getId());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("update error.");

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    // Aggiorna la passoword del customer
    public void doUpdatePassword(Customer c){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "update cliente set passwordUtente=? where id=?", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, c.getPassword());
            ps.setInt(2,c.getId());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("update error.");

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    // Verifica che la vecchia password inserita sia uguale a quella presente nel database
    public boolean CheckPassword(Customer c){

        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "select passwordUtente from cliente where id=?", Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1,c.getId());

            ResultSet rs = ps.executeQuery();
            String password = null;

            if (rs.next())
                password = rs.getString(1);

            if(password != null && password.equals(c.getPassword()))
                return true;

            return false;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}

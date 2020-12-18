package model;

import java.sql.*;
import java.util.*;

public class ProdottoCarrelloDAO {

    /* Returna tutte le auto contenute nel carrello di un determinato cliente */
    public List<ProdottoCarrello> doRetrieveCarrelloByIdCliente(int idCliente) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select * from prodottoCarrello where cliente = ?");
            ps.setInt(1, idCliente);

            ResultSet rs = ps.executeQuery();

            List<ProdottoCarrello> auto = new ArrayList<>();

            while (rs.next()) {

                ProdottoCarrello autoCarrello = new ProdottoCarrello();

                String nomeAuto = rs.getString(2);
                String casaAuto = rs.getString(3);
                double prezzo = rs.getDouble("prezzo");
                int quantita = rs.getInt("quantita");

                autoCarrello.setNomeAuto(nomeAuto);
                autoCarrello.setCasaAuto(casaAuto);
                autoCarrello.setPrezzo(prezzo);
                autoCarrello.setQuantita(quantita);

                auto.add(autoCarrello);
            }

            return auto;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    // Rimuove un prodotto dal carrello
    public void doRemoveProdottoCarrello(ProdottoCarrello auto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("delete from prodottocarrello where nomeAuto = ? and casaAuto = ? and cliente = ?");

            ps.setString(1, auto.getNomeAuto());
            ps.setString(2, auto.getCasaAuto());
            ps.setInt(3, auto.getIdCliente());

            ps.executeUpdate();

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    /* Permette all'utente di inserire un'auto all'interno del suo carrello anche quando non ha fatto l'accesso*/
    public void doSaveProdottoCarrelloByIdNomeAutoCasaAuto(int idCliente, List<ProdottoCarrello> auto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "insert into prodottoCarrello(cliente, nomeAuto, casaAuto, prezzo) values(?, ?, ?, ?)");
            PreparedStatement ps1 = con.prepareStatement("select quantita from prodottoCarrello " +
                    "where cliente = ? and nomeAuto = ? and casaAuto = ?");
            PreparedStatement ps2 = con.prepareStatement("update prodottoCarrello " +
                    "set quantita = ?, prezzo = ? where cliente = ? and nomeAuto = ? and casaAuto = ?");

            for (ProdottoCarrello c : auto) {

                if (!doCheckCarrello(idCliente, c)) {
                    ps.setInt(1, idCliente);
                    ps.setString(2, c.getNomeAuto());
                    ps.setString(3, c.getCasaAuto());
                    ps.setDouble(4, c.getPrezzoDouble());

                    if (ps.executeUpdate() != 1)
                        throw new RuntimeException("insert error.");
                }

                else {

                    ps1.setInt(1, idCliente);
                    ps1.setString(2, c.getNomeAuto());
                    ps1.setString(3, c.getCasaAuto());

                    ResultSet rs = ps1.executeQuery();
                    rs.next();
                    int quantita = rs.getInt("quantita");
                    quantita++;

                    double prezzo1 = c.getPrezzoDouble() * quantita;


                    ps2.setInt(1, quantita);
                    ps2.setDouble(2, prezzo1);
                    ps2.setInt(3, idCliente);
                    ps2.setString(4, c.getNomeAuto());
                    ps2.setString(5, c.getCasaAuto());

                    if (ps2.executeUpdate() != 1)
                        throw new RuntimeException("update error.");
                }

            }

        } catch(SQLException s) {

            throw new RuntimeException(s);
        }
    }

    /* Permette all'utente di inserire quando ha fatto l'accesso */
    public void doSaveProdottoCarrelloByIdNomeAutoCasaAuto1(int idCliente, ProdottoCarrello auto) {

        try (Connection con = ConPool.getConnection()) {

            if (doCheckCarrello(idCliente, auto)) {

                /* Query per estrarre il valore */
                PreparedStatement ps = con.prepareStatement(
                        "select quantita from prodottoCarrello " +
                                "where cliente = ? and nomeAuto = ? and casaAuto = ?");

                ps.setInt(1, idCliente);
                ps.setString(2, auto.getNomeAuto());
                ps.setString(3, auto.getCasaAuto());

                ResultSet rs = ps.executeQuery();
                rs.next();
                int quantita = rs.getInt("quantita");
                quantita++;

                double prezzoFinale = auto.getPrezzoDouble() * quantita;

                /* Query per aggiornare */
                PreparedStatement ps2 = con.prepareStatement(
                        "update prodottoCarrello set quantita = ?, prezzo = ? where cliente = ? and nomeAuto = ? and casaAuto = ?");

                ps2.setInt(1, quantita);
                ps2.setDouble(2, prezzoFinale);
                ps2.setInt(3, idCliente);
                ps2.setString(4, auto.getNomeAuto());
                ps2.setString(5, auto.getCasaAuto());

                if (ps2.executeUpdate() != 1)
                    throw new RuntimeException("insert error.");
            }

            else {

                PreparedStatement ps = con.prepareStatement(
                        "insert into prodottoCarrello(cliente, nomeAuto, casaAuto, prezzo) values(?, ?, ?, ?)");

                ps.setInt(1, idCliente);
                ps.setString(2, auto.getNomeAuto());
                ps.setString(3, auto.getCasaAuto());
                ps.setDouble(4, auto.getPrezzoDouble());

                if (ps.executeUpdate() != 1)
                    throw new RuntimeException("insert error.");
            }

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }

    }

    // Query che controlla se l'articolo è presente nel carrello dell'utente(Così si aggiunge oppure si aggiorna)
    private boolean doCheckCarrello(int idCliente, ProdottoCarrello auto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "select count(*) from prodottoCarrello " +
                            "where cliente = ? and nomeAuto = ? and casaAuto = ?");

            ps.setInt(1, idCliente);
            ps.setString(2, auto.getNomeAuto());
            ps.setString(3, auto.getCasaAuto());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getInt(1) == 1)
                    return true;
                else
                    return false;
            }

            return true;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }




    public void doSaveProdottoCarrelloByIdNomeAutoCasaAuto2(int idCliente, ProdottoCarrello auto) {

        try (Connection con = ConPool.getConnection()) {

            if (doCheckCarrello(idCliente, auto)) {

                System.out.println("coglione");
                /* Query per estrarre il valore */


                PreparedStatement ps1 = con.prepareStatement(
                        "select prezzo from auto " +
                                "where nome = ? and casaAuto = ?");

                ps1.setString(1, auto.getNomeAuto());
                ps1.setString(2, auto.getCasaAuto());

                ResultSet rs1 = ps1.executeQuery();
                rs1.next();
                double prezzo = rs1.getDouble("prezzo");

                System.out.println(auto.getPrezzoDouble());
                double prezzoFinale = prezzo * auto.getQuantita();

                /* Query per aggiornare */
                PreparedStatement ps2 = con.prepareStatement(
                        "update prodottoCarrello set quantita = ?, prezzo = ? where cliente = ? and nomeAuto = ? and casaAuto = ?");

                ps2.setInt(1, auto.getQuantita());
                ps2.setDouble(2, prezzoFinale);
                ps2.setInt(3, idCliente);
                ps2.setString(4, auto.getNomeAuto());
                ps2.setString(5, auto.getCasaAuto());

                if (ps2.executeUpdate() != 1)
                    throw new RuntimeException("insert error.");
            }

            else {

                PreparedStatement ps = con.prepareStatement(
                        "insert into prodottoCarrello(cliente, nomeAuto, casaAuto, prezzo) values(?, ?, ?, ?)");


                ps.setInt(1, idCliente);
                ps.setString(2, auto.getNomeAuto());
                ps.setString(3, auto.getCasaAuto());
                ps.setDouble(4, auto.getPrezzoDouble());

                if(ps.executeUpdate() != 1)
                    throw new RuntimeException("insert error.");
            }

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }

    }
}

package model;

import java.sql.*;
import java.util.*;

public class ProdottoPreferitoDAO {

    /* Returna tutte le auto preferite da un determinato cliente */
    public List<ProdottoPreferito> doRetrievePreferitiByIdCliente(int idCliente) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select * from prodottoPreferito where cliente = ?");
            ps.setInt(1, idCliente);

            ResultSet rs = ps.executeQuery();

            CarDAO cd = new CarDAO();
            List<ProdottoPreferito> auto = new ArrayList<>();

            while (rs.next()) {

                Car car;
                ProdottoPreferito autoPreferita = new ProdottoPreferito();

                String nomeAuto = rs.getString(2);
                String casaAuto = rs.getString(3);

                car = cd.doRetrieveAutoByNomeCasaAuto(nomeAuto, casaAuto);
                autoPreferita.setNomeAuto(car.getNome());
                autoPreferita.setCasaAuto(car.getCasaAuto());

                auto.add(autoPreferita);
            }

            return auto;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    /* Permette all'utente di inserire un'auto tra i preferiti quando ancora non ha fatto l'accesso */
    public void doSaveProdottoPreferitoByIdClienteNomeAutoCasaAuto(int idCliente, List<ProdottoPreferito> auto) {

        try (Connection con = ConPool.getConnection()) {

            if (auto != null) {
                PreparedStatement ps = con.prepareStatement(
                        "insert into prodottoPreferito(cliente, nomeAuto, casaAuto) values(?, ?, ?)");

                for (ProdottoPreferito c : auto) {

                    /* Se la macchina non è già presente la inserisce altrimenti no */
                    if (doCheckPreferito(idCliente, c)) {
                        ps.setInt(1, idCliente);
                        ps.setString(2, c.getNomeAuto());
                        ps.setString(3, c.getCasaAuto());

                        ps.executeUpdate();
                    }
                }
            }

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    /* Permette all'utente di inserire quando ha fatto l'accesso */
    public boolean doSaveProdottoPreferitoByIdClienteNomeAutoCasaAuto1(int idCliente, ProdottoPreferito auto) {

        /* Quando facciamo l'accesso se la macchina non è tra i preferiti la inserisce altrimenti no */
        try (Connection con = ConPool.getConnection()) {

            if (doCheckPreferito(idCliente, auto)) {
                PreparedStatement ps = con.prepareStatement(
                        "insert into prodottoPreferito(cliente, nomeAuto, casaAuto) values(?, ?, ?)");

                ps.setInt(1, idCliente);
                ps.setString(2, auto.getNomeAuto());
                ps.setString(3, auto.getCasaAuto());

                ps.executeUpdate();

                return true;
            }

            else
                return false;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    // Permetti di verificare se l'articolo è tra i preferiti oppure no
    private boolean doCheckPreferito(int idCliente, ProdottoPreferito auto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "select count(*) from prodottoPreferito " +
                            "where cliente = ? and nomeAuto = ? and casaAuto = ?");

            ps.setInt(1, idCliente);
            ps.setString(2, auto.getNomeAuto());
            ps.setString(3, auto.getCasaAuto());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getInt(1) == 1)
                    return false;
                else
                    return true;
            }

            return true;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    // Permette di rimuovere un articolo dai preferiti
    public void doRemoveProdottoPreferito(ProdottoPreferito auto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("delete from prodottopreferito where nomeAuto = ? and casaAuto = ? and cliente = ?");

            ps.setString(1, auto.getNomeAuto());
            ps.setString(2, auto.getCasaAuto());
            ps.setInt(3, auto.getIdCliente());

            ps.executeUpdate();

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }
}

package model;

import java.sql.*;
import java.util.*;

public class ProdottoAcquistoDAO {

    /* Returna tutte le auto contenute tra gli acquisti di un determinato cliente */
    public List<ProdottoAcquisto> doRetrieveAcquistiByIdCliente(int idCliente) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select * from prodottoAcquisto where cliente = ?");
            ps.setInt(1, idCliente);

            ResultSet rs = ps.executeQuery();

            List<ProdottoAcquisto> auto = new ArrayList<>();
            ProdottoAcquisto autoAcquisto;

            while (rs.next()) {

                autoAcquisto = new ProdottoAcquisto();

                int codice = rs.getInt(1);
                String nomeAuto = rs.getString(2);
                String casaAuto = rs.getString(3);
                double prezzo = rs.getDouble(5);
                int quantita = rs.getInt(6);
                String data = rs.getString(7);

                autoAcquisto.setIdAcquisto(codice);
                autoAcquisto.setIdCliente(idCliente);
                autoAcquisto.setNomeAuto(nomeAuto);
                autoAcquisto.setCasaAuto(casaAuto);
                autoAcquisto.setPrezzoTotale(prezzo);
                autoAcquisto.setQuantita(quantita);
                autoAcquisto.setData(data);

                auto.add(autoAcquisto);
            }

            return auto;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    /* Salva un acquisto nel database */
    public void doSaveProdottoAcquisto(ProdottoAcquisto auto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "insert into prodottoAcquisto(nomeAuto, casaAuto, cliente, prezzo, quantita, data) values(?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, auto.getNomeAuto());
            ps.setString(2, auto.getCasaAuto());
            ps.setInt(3, auto.getIdCliente());
            ps.setDouble(4,auto.getPrezzoTotale());
            ps.setInt(5,auto.getQuantita());
            ps.setString(6,auto.getData());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("insert error.");

        } catch(SQLException s) {

            throw new RuntimeException(s);
        }
    }


    /* Salva un acquisto nel database e cancella i prodotti nel carrello*/
    public void doSaveProdottoAcquistoAndDelete(ProdottoAcquisto auto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "insert into prodottoAcquisto(nomeAuto, casaAuto, cliente, prezzo, quantita, data) values(?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, auto.getNomeAuto());
            ps.setString(2, auto.getCasaAuto());
            ps.setInt(3, auto.getIdCliente());
            ps.setDouble(4,auto.getPrezzoTotale());
            ps.setInt(5,auto.getQuantita());
            ps.setString(6,auto.getData());

            if (ps.executeUpdate() != 1)
                throw new RuntimeException("insert error.");


            PreparedStatement ps1 = con.prepareStatement("delete from prodottocarrello where cliente=?");
            ps1.setInt(1, auto.getIdCliente());
            ps1.executeUpdate();

        } catch(SQLException s) {

            throw new RuntimeException(s);
        }
    }
}


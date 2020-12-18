package model;

import java.sql.*;
import java.util.*;

public class CarDAO {

    // Query per prelevare tutte le auto
    public List<Car> doRetrieveAllCars() {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select * from auto");
            ResultSet rs = ps.executeQuery();

            List<Car> auto = new ArrayList<>();
            Car c;

            while (rs.next()) {

                c = new Car();
                c.setNome(rs.getString("nome"));
                c.setCasaAuto(rs.getString("casaAuto"));
                c.setPrezzo(rs.getDouble("prezzo"));
                auto.add(c);
            }

            return auto;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }


    }

    /* Returna un oggetto auto prendendo in input il nome dell'auto e la casa produttrice */
    public Car doRetrieveAutoByNomeCasaAuto(String nomeAuto, String casaAuto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select * from auto where nome = ? and casaAuto = ?");

            ps.setString(1, nomeAuto);
            ps.setString(2, casaAuto);

            ResultSet rs = ps.executeQuery();

            Car c = new Car();

            while (rs.next()) {

                c = new Car();
                c.setNome(rs.getString(1));
                c.setCasaAuto(rs.getString(2));
                c.setAlimentazione(rs.getString(3));
                c.setTrasmissione(rs.getString(4));
                c.setPrezzo(rs.getInt("prezzo"));

            }

            return c;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    /* Returna tutte le marche */
    public List<String> retrieveMarche() {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select distinct casaAuto from auto");
            ResultSet rs = ps.executeQuery();

            List<String> marche = new ArrayList<>();

            while (rs.next())
                marche.add(rs.getString(1));

            return marche;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    /* Returna tutti i nomi delle auto */
    public List<String> retrieveNomi() {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select distinct nome from auto");
            ResultSet rs = ps.executeQuery();

            List<String> nomi = new ArrayList<>();

            while (rs.next())
                nomi.add(rs.getString(1));

            return nomi;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    /* Returna la velocità massime per le auto di quella marca */
    public List<Integer> retrieveVelocita(String casaAuto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select distinct velocitaMassima " +
                    "from auto " +
                    "where casaAuto = ?");

            ps.setString(1, casaAuto);
            ResultSet rs = ps.executeQuery();

            List<Integer> velocita = new ArrayList<>();

            while (rs.next())
                velocita.add(rs.getInt(1));

            return velocita;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    /* Returna una lista con tutte le categorie delle auto di quella marca */
    public List<String> retrieveCategorie(String casaAuto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select distinct categoria " +
                    "from auto " +
                    "where casaAuto = ?");

            ps.setString(1, casaAuto);
            ResultSet rs = ps.executeQuery();

            List<String> categorie = new ArrayList<>();

            while (rs.next()) {

                String s = rs.getString(1).substring(0, 1).toUpperCase();
                String categoria = s + rs.getString(1).substring(1);

                categorie.add(categoria);
            }

            return categorie;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }

    /* Returna una lista contenente tutte le auto di una data marca */
    public List<Car> retrieveAutoMarca(String casaAuto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select nome, casaAuto, velocitaMassima, categoria, prezzo " +
                    "from auto " +
                    "where casaAuto = ?");

            ps.setString(1, casaAuto);
            ResultSet rs = ps.executeQuery();

            List<Car> auto = new ArrayList<>();

            while (rs.next()) {

                Car c = new Car();

                c.setNome(rs.getString(1));
                c.setCasaAuto(rs.getString(2));
                c.setVelMax(rs.getInt(3));
                c.setCategoria(rs.getString(4));
                c.setPrezzo(rs.getDouble(5));
                auto.add(c);
            }

            return auto;

        } catch(SQLException s) {

            throw new RuntimeException(s);
        }
    }

    // Returna una lista con le auto compatibili con quei filtri
    public List<Car> retrieveAutoFilter(String casaAuto, String categoria, Double prezzoMin, Double prezzoMax,
                                        Integer velocitaMin, Integer velocitaMAx, String ordinaprezzo) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = null;
            System.out.println(casaAuto);

            if (casaAuto != null && !casaAuto.equals("AllCars")) {
                if (categoria != null) {
                    ps = con.prepareStatement("select nome, casaAuto, velocitaMassima, categoria, prezzo " +
                            "from auto " +
                            "where casaAuto = ? and categoria= ? and velocitaMassima<= ? and velocitaMassima>= ? and prezzo<= ? and prezzo>= ?" +
                            "ORDER BY " + ordinaprezzo);
                    ps.setString(1, casaAuto);
                    ps.setString(2, categoria);
                    ps.setInt(3, velocitaMAx);
                    ps.setInt(4, velocitaMin);
                    ps.setDouble(5, prezzoMax);
                    ps.setDouble(6, prezzoMin);

                }

                else {

                    ps = con.prepareStatement("select nome, casaAuto, velocitaMassima, categoria, prezzo " +
                            "from auto " +
                            "where casaAuto = ?  and velocitaMassima<= ? and velocitaMassima>= ? and prezzo<= ? and prezzo>= ?" +" ORDER BY " + ordinaprezzo);
                    ps.setString(1, casaAuto);
                    ps.setInt(2, velocitaMAx);
                    ps.setInt(3, velocitaMin);
                    ps.setDouble(4, prezzoMax);
                    ps.setDouble(5, prezzoMin);

                }
            }

            else if (casaAuto.equals("AllCars")) {

                if (categoria != null) {
                    ps = con.prepareStatement("select nome, casaAuto, velocitaMassima, categoria, prezzo " +
                            "from auto " +
                            "where categoria= ? and velocitaMassima<= ? and velocitaMassima>= ? and prezzo<= ? and prezzo>= ?" +
                            "ORDER BY " + ordinaprezzo);
                    ps.setString(1, categoria);
                    ps.setInt(2, velocitaMAx);
                    ps.setInt(3, velocitaMin);
                    ps.setDouble(4, prezzoMax);
                    ps.setDouble(5, prezzoMin);
                    System.out.println(ps);

                }

                else {

                    ps = con.prepareStatement("select nome, casaAuto, velocitaMassima, categoria, prezzo " +
                            "from auto " +
                            "where velocitaMassima<= ? and velocitaMassima>= ? and prezzo<= ? and prezzo>= ?" +" ORDER BY " + ordinaprezzo);
                    ps.setInt(1, velocitaMAx);
                    ps.setInt(2, velocitaMin);
                    ps.setDouble(3, prezzoMax);
                    ps.setDouble(4, prezzoMin);
                    System.out.println(ps);
                }
            }

            ResultSet rs = ps.executeQuery();

            List<Car> auto = new ArrayList<>();

            while (rs.next()) {

                Car c = new Car();

                c.setNome(rs.getString(1));
                c.setCasaAuto(rs.getString(2));
                c.setVelMax(rs.getInt(3));
                c.setCategoria(rs.getString(4));
                c.setPrezzo(rs.getDouble(5));
                auto.add(c);
            }

            return auto;

        } catch(SQLException s) {

            throw new RuntimeException(s);
        }
    }

    /* Returna una lista con tutti i prezzi di auto di quella marca */
    public List<Double> retrievePrezzi(String casaAuto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select distinct prezzo " +
                    "from auto " +
                    "where casaAuto = ?");

            ps.setString(1, casaAuto);
            ResultSet rs = ps.executeQuery();

            List<Double> prezzi = new ArrayList<>();

            while (rs.next()){
                prezzi.add(rs.getDouble(1));
            }


            return prezzi;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }


    /* Returna un oggetto auto prendendo in input il nome dell'auto e la casa produttrice con tutti i parametri settati */
    public Car doRetrieveAutoByNCAllParameters(String nomeAuto, String casaAuto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select * from auto where nome = ? and casaAuto = ?");

            ps.setString(1, nomeAuto);
            ps.setString(2, casaAuto);

            ResultSet rs = ps.executeQuery();

            Car c = new Car();

            while (rs.next()) {

                c.setNome(rs.getString(1));
                c.setCasaAuto(rs.getString(2));
                c.setAlimentazione(rs.getString(3));
                c.setTrasmissione(rs.getString(4));
                c.setCategoria(rs.getString(5));
                c.setLunghezza(rs.getDouble(6));
                c.setLarghezza(rs.getDouble(7));
                c.setAltezza(rs.getDouble(8));
                c.setPorte(rs.getInt(9));
                c.setPosti(rs.getInt(10));
                c.setPeso(rs.getInt(11));
                c.setCilindri(rs.getInt(12));
                c.setCilindrata(rs.getInt(13));
                c.setPotenza(rs.getInt(14));
                c.setRapporti(rs.getInt(15));
                c.setVelMax(rs.getInt(17));
                c.setAccelerazione(rs.getDouble(18));
                c.setEmissioneCO2(rs.getInt(19));
                c.setkW(rs.getInt(20));
                c.setPrezzo(rs.getDouble(21));
            }

            return c;

        } catch (SQLException s) {

            throw new RuntimeException(s);
        }
    }
}

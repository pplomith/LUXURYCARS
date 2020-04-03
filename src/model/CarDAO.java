package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CarDAO {

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

    public List<Car> retrieveAutoMarca(String casaAuto) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("select nome, casaAuto, velocitaMassima, categoria " +
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

                auto.add(c);
            }

            return auto;

        } catch(SQLException s) {

            throw new RuntimeException(s);
        }

    }
}

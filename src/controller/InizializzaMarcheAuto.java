package controller;

import model.CarDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.*;

// Servlet iniziale, dove vengono caricate dal DB le marche e i nomi delle auto disponibili
@WebServlet(name = "MyInit", urlPatterns = "/MyInit", loadOnStartup = 0)
public class InizializzaMarcheAuto extends HttpServlet {

    public void init() throws ServletException {

        CarDAO carDAO = new CarDAO();
        List<String> marcheAuto = carDAO.retrieveMarche(); //salvataggio nel ServletContext delle marche e nomi di auto
        List<String> nomiAuto = carDAO.retrieveNomi();

        // Settate nel servletContext in modo da essere visibili in tutte le servlet e per tutti gli utenti

        getServletContext().setAttribute("marcheAuto", marcheAuto);
        getServletContext().setAttribute("nomiAuto", nomiAuto);
        super.init();
    }
}

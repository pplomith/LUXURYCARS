package controller;

import model.CarDAO;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "MyInit", urlPatterns = "/MyInit", loadOnStartup = 0)
public class InizializzaMarcheAuto extends HttpServlet {

    public void init() throws ServletException {

        CarDAO carDAO = new CarDAO();
        List<String> marcheAuto = carDAO.retrieveMarche();
        getServletContext().setAttribute("marcheAuto", marcheAuto);
        super.init();
    }
}

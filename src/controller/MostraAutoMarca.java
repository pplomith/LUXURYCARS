package controller;

import model.*;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Servlet che mostra le auto per marca
@WebServlet("/show-auto-marca")
public class MostraAutoMarca extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


            List<String> marcheAuto = (List<String>)getServletContext().getAttribute("marcheAuto"); //presa dal ServletContext le marche delle auto
            CarDAO carDAO = new CarDAO();

            // Ciclo for per individuare la marca giusta
            int i;
            for (i = 0; i < marcheAuto.size(); i++) {
                if (request.getParameter(marcheAuto.get(i)) != null) //marca auto inserita nella request (indicata nel form della marca spicifica, name del button)
                    break;
            }

            // Prelievo delle auto dal database con quella marca

            List<Car> auto = carDAO.retrieveAutoMarca(marcheAuto.get(i));
            request.setAttribute("auto", auto);

            // Prelievo delle velocità e categorie, creazione pathImmagini delle auto di quella marca

            List<Integer> maxSpeed = carDAO.retrieveVelocita(marcheAuto.get(i));
            List<String> categorie = carDAO.retrieveCategorie(marcheAuto.get(i));
            List<String> pathImmagini = new ArrayList<>();

            // Settaggio dei path delle immagini

            for (Car a : auto) {
                a.setPath("./images/car_marche/", "", ".png");
                pathImmagini.add(a.getPath());
            }

            // Inserimento nella request degli attributi

            request.setAttribute("maxSpeed", maxSpeed);
            request.setAttribute("categorie", categorie);
            request.setAttribute("pathImmagini", pathImmagini);

            // Settaggio della stringa di reindirizzamento e reindirizzamento
            String address = "/WEB-INF/results/show-auto-marca.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);

            dispatcher.forward(request, response);

    }
}

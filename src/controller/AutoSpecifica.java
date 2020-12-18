package controller;

import model.Car;
import model.CarDAO;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

// Servlet per visualizzare l'auto specifica
@WebServlet("/visualizza-auto")
public class AutoSpecifica extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> nomiAuto = (List<String>)getServletContext().getAttribute("nomiAuto"); //presa delle marche dal ServletContext

        int i;

        // Quando il nome nome dell'auto è diverso da null allora significa che l'auto è quella
        for (i = 0; i < nomiAuto.size(); i++) {
            if (request.getParameter(nomiAuto.get(i)) != null)
                break;
        }

        // Salvataggio del nome dell'auto e della casa dell'auto con successiva creazione del bean

        String nomeAuto = nomiAuto.get(i);
        String casaAuto = request.getParameter("marcaAuto");
        CarDAO cda = new CarDAO();
        Car auto = cda.doRetrieveAutoByNCAllParameters(nomeAuto,casaAuto);

        // Dichiarazione variabili che conterranno i path delle foto

        String pathFront;
        String pathEngine;
        String pathAnother;
        String pathSide;

        // Caricamento dei path delle immagini

        auto.setPath("./images/car_specifiche/", "front", ".jpg");
        pathFront = auto.getPath();
        auto.setPath("./images/car_specifiche/", "engine", ".jpg");
        pathEngine = auto.getPath();
        auto.setPath("./images/car_specifiche/", "another", ".jpg");
        pathAnother = auto.getPath();
        auto.setPath("./images/car_specifiche/", "side", ".jpg");
        pathSide = auto.getPath();


        // Salvataggio degli attributi nella request: path delle immagini e attributo auto

        request.setAttribute("pathAnother", pathAnother);
        request.setAttribute("pathFront", pathFront);
        request.setAttribute("pathEngine", pathEngine);
        request.setAttribute("pathSide", pathSide);
        request.setAttribute("auto", auto);


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/results/car.jsp");
        dispatcher.forward(request, response);
    }
}

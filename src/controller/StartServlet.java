package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

import model.*;

// Servlet per inizializzare gli attribute preferitiDaSalvere e carrelloDaSalvare
@WebServlet("/index.html")
public class StartServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true); //creazione sessione

        List<ProdottoPreferito> preferiti = new ArrayList<>(); //preferiti che verranno salvati nella sessione prima dell'accesso
        List<ProdottoCarrello> carrello = new ArrayList<>(); //carrello che verrà salvato nella sessione prima dell'accesso

        /*
            Inserimento nella sessione di preferitiDaSalvare e carrelloDaSalvare che verranno usati successivamente
        */
        session.setAttribute("preferitiDaSalvare", preferiti);
        session.setAttribute("carrelloDaSalvare", carrello);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request,response);
    }
}
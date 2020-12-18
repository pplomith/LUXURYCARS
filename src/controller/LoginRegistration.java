package controller;

import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

// Servlet che serve per gestire l'utente, viene invocata quando si clicca l'omini della nav bar, quindi un login semplice o accesso all'area personale
@WebServlet("/login-registration")
public class LoginRegistration extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Creazione della sessione e settaggio di acquistoProssimo e accessoCarrello a 0
        HttpSession session = request.getSession(false);
        int acquistoProssimo = 0; //variabile per fare l'acquisto diretto, senza passare per il carrello
        int accessoCarrello = 0; //variabile per vedere se si sta accedendo dal carrello
        int errorLog = 0;

        // Salvataggio degli attributi nella request
        request.setAttribute("acquistoProssimo", acquistoProssimo);
        request.setAttribute("accessoCarrello", accessoCarrello);
        request.setAttribute("errorLog", errorLog);
        RequestDispatcher dispatcher;
        CustomerDAO cdao = new CustomerDAO();

        // Prelievo dalla request del parametro "submit_esci" e verifica se è null. Se non è null allora esce
        if (request.getParameter("submit_esci") != null) {
            if (request.getParameter("submit_esci").equals("Esci")) {
                session.setAttribute("idCliente", null);
                dispatcher = getServletContext().getRequestDispatcher("/index.html");
                dispatcher.forward(request, response);
            }
        }

        /*
            Prelievo dalla request del parametro "submit_delete" e verifica se è null. Se non è null allora cancella
            il profilo
        */
        else if (request.getParameter("submit_delete") != null) {

            if (request.getParameter("submit_delete").equals("Elimina profilo")) {
                int c = (Integer) session.getAttribute("idCliente");
                Customer customer = cdao.doRetrieveById(c);
                cdao.doRemoveCustomer(customer);
                session.setAttribute("idCliente", null);
                dispatcher = getServletContext().getRequestDispatcher("/index.html");
                dispatcher.forward(request, response);
            }
        }

        // Se l'id del cliente è null ti reinvia al login.jsp
        else if (session.getAttribute("idCliente") == null) {
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/results/login.jsp");
            dispatcher.forward(request, response);
        }

        // Altrimenti accede all'area riservata
        else {

            ProdottoPreferitoDAO pfd = new ProdottoPreferitoDAO();
            ProdottoAcquistoDAO pad = new ProdottoAcquistoDAO();

            int c = (Integer) session.getAttribute("idCliente");
            Customer customer = cdao.doRetrieveById(c);

            // Prelievo dei preferiti e degli articoli nel carrello per l'utente
            List<ProdottoPreferito> preferiti = pfd.doRetrievePreferitiByIdCliente(c);
            List<ProdottoAcquisto> acquisti = pad.doRetrieveAcquistiByIdCliente(c);

            // Settaggio dei path per le immagini
            for (ProdottoAcquisto pc : acquisti)
                pc.setPath("./images/car_marche/", "", ".png");
            for (ProdottoPreferito pc : preferiti)
                pc.setPath("./images/car_marche/", "", ".png");

            // Inserimento degli attributi nella request
            request.setAttribute("customer", customer);
            request.setAttribute("acquisti", acquisti);
            request.setAttribute("preferiti", preferiti);

            // Indirizzamento dell'utente alla jsp
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/results/userProfile.jsp");
            dispatcher.forward(request, response);
        }
    }
}

package controller;

import model.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

// Servlet per effettuare l'accesso nel carrello, viene attivata quando si clicca sull'icona del carrello nella nav bar
@WebServlet("/servlet-accesso-carrello")
public class ServletAccessoCarrello extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher;

        // Se l'id utente è null deve effettuare l'accesso, e viene settato ad 1 la variabile per accedere al carrello
        if (session.getAttribute("idCliente") == null) {

            int accessoCarrello = 1; //variabiler per accedere al carrello
            int acquistoProssimo = 0; //variabile per effettuare l'ascquisto diretto

            // Reindirizzamento alla pagina di login
            request.setAttribute("acquistoProssimo", acquistoProssimo);
            request.setAttribute("accessoCarrello", accessoCarrello);
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/results/login.jsp");
            dispatcher.forward(request, response);
        }

        // Se l'id utente non è null, quindi ha effettuato l'accesso di conseguenza va nel carrello
        else {

            // Vengono prelevate tutte le auto nel carrello di quell'utente
            ProdottoCarrelloDAO pcd = new ProdottoCarrelloDAO();
            Double prezzoTotale = 0.0;
            List<ProdottoCarrello> carrello =  pcd.doRetrieveCarrelloByIdCliente(
                    Integer.parseInt(session.getAttribute("idCliente").toString()));

            // Per ogni prodotto nel carrello viene creata un'immmagine
            for (ProdottoCarrello pc : carrello)
                pc.setPath("./images/car_marche/", "", ".png");

            // Viene settato il prezzo totale nel carrello
            for (int i = 0; i < carrello.size(); i++)
                prezzoTotale += carrello.get(i).getPrezzoDouble();

            // Viene formattato il prezzo totale
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
            String prezzoTot = currencyFormatter.format(prezzoTotale);

            // Salvataggio degli attributi nella request
            request.setAttribute("prezzoTotale",prezzoTot);
            request.setAttribute("carrello", carrello);

            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/results/carrello.jsp");
            dispatcher.forward(request, response);
        }
    }
}

package controller;

import model.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

// Servlet usata per aggiungere elementi nel carrello
@WebServlet("/ServletCarrello")
public class ServletCarrello extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Prelievo dei parametri dalla request
        String nomeAuto = request.getParameter("nomeAuto");
        String casaAuto = request.getParameter("casaAuto");


        HttpSession session = request.getSession(false);

        CarDAO cd = new CarDAO();
        Car auto = cd.doRetrieveAutoByNomeCasaAuto(nomeAuto, casaAuto);
        List<ProdottoCarrello> carrelloDaSalvare = (List<ProdottoCarrello>)session.getAttribute("carrelloDaSalvare"); /* prende elementi dalla sessione
                                                                                                                                se ci sono*/

        // Settaggio del testo della risposta a xml
        response.setContentType("text/xml;charset=UTF-8");

        /* Se l'utente ancora non ha effettuato l'accesso, salva il carrello nella sessione */
        if (session.getAttribute("idCliente") == null) {


            ProdottoCarrello autoCarrello = new ProdottoCarrello();
            autoCarrello.setNomeAuto(auto.getNome());
            autoCarrello.setCasaAuto(auto.getCasaAuto());
            autoCarrello.setPrezzo(auto.getPrezzoDouble());
            carrelloDaSalvare.add(autoCarrello);

            session.setAttribute("carrelloDaSalvare", carrelloDaSalvare);

            response.getWriter().append("<okAccesso/>");

        }

        /* altrimenti l'utente ha già fatto l'accesso quindi i prodotti vengono salvati nel database*/
        else {

            ProdottoCarrelloDAO pfd = new ProdottoCarrelloDAO();
            /*
                 Se l'utente ha fatto l'accesso conosciamo il suo id e quindi possiamo salvare
                 direttamente il tutto
            */
            int idCliente = (int)session.getAttribute("idCliente");

            ProdottoCarrello pc = new ProdottoCarrello();

            pc.setNomeAuto(nomeAuto);
            pc.setCasaAuto(casaAuto);
            pc.setPrezzo(auto.getPrezzoDouble());
            pfd.doSaveProdottoCarrelloByIdNomeAutoCasaAuto1(idCliente, pc);
            response.getWriter().append("<ok/>");
        }
    }
}

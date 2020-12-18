package controller;

import model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

// Servlet per effettuare un acquisto diretto
@WebServlet("/ServletAcquisto")
public class ServletAcquisto extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        String nomeAuto = request.getParameter("nomeAuto");
        String casaAuto = request.getParameter("casaAuto");

        HttpSession session = request.getSession(false);

        /*
            Se si vuole effettuare un acquisto la persona deve prima accedere
            una volta che ha effettuato l'accesso può acquistare
        */
        if (session.getAttribute("idCliente") == null) {

            int acquisto = 1; //variabiler per acquistare direttamente
            int accessoCarrello = 0; //variabile per accedere al carrello


            //Car autoSpecfica = cadao.doRetrieveAutoByNomeCasaAuto(nomeAuto,casaAuto);

            ProdottoAcquisto auto = new ProdottoAcquisto();
            auto.setNomeAuto(nomeAuto);
            auto.setCasaAuto(casaAuto);


            // Inserimento nella request di attributi AcquistoProssimo e AccessoCarrello
            request.setAttribute("auto", auto);
            request.setAttribute("acquistoProssimo", acquisto);
            request.setAttribute("accessoCarrello", accessoCarrello);

            // Indirizzamento al login
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/results/login.jsp");
            dispatcher.forward(request, response);
        }

        /*
            Se ha già effettuato l'accesso non c'è necessità di rieffettuarlo e quindi l'utente sarà
            indirizzato direttamente alla pagina per l'acquisto
        */

        else {

            CarDAO carDao = new CarDAO();
            Car car = carDao.doRetrieveAutoByNomeCasaAuto(nomeAuto, casaAuto);

            ProdottoAcquisto auto = new ProdottoAcquisto();

            // Creazione della data dell'acquisto
            GregorianCalendar calendar = new GregorianCalendar();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String DataAcquisto = format.format(calendar.getTime());

            // Settaggio degli attributi del bean
            auto.setNomeAuto(nomeAuto);
            auto.setCasaAuto(casaAuto);
            auto.setQuantita(1);
            auto.setPrezzoTotale(car.getPrezzoDouble());
            auto.setIdCliente((Integer)session.getAttribute("idCliente"));
            auto.setData(DataAcquisto);

            // Inserimento del bean nell'array prodotti
            List<ProdottoAcquisto> prodotti = new ArrayList<>();
            prodotti.add(auto);

            // Inserimento dei prodotti(un prodotto solo) nella request
            request.setAttribute("prodotti", prodotti);
            request.setAttribute("prezzoTot",auto.getPrezzoTotaleString());
            session.setAttribute("prodotti", prodotti);
            request.setAttribute("diretto", 1);

            // Indirizzamento al checkout
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/results/checkout.jsp");
            dispatcher.forward(request, response);
        }
    }
}

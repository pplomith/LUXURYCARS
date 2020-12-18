package controller;

import model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

// Servlet per mandare l'utente al check out
@WebServlet("/OrdinaCarrello")
public class AcquistoDaCarrello extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int idCliente = (Integer) session.getAttribute("idCliente");

        // Vengono prese le auto dal carrello e inviate al check out

        ProdottoCarrelloDAO pcd = new ProdottoCarrelloDAO();
        List<ProdottoCarrello> carrello = pcd.doRetrieveCarrelloByIdCliente(idCliente);

        List<ProdottoAcquisto> prodotti = new ArrayList<>();
        double prezzoTotale = 0.0;

        // Settaggio dei bean che rappresentano i prodotti acquisto e loro inserimento nell'array

        for(int i = 0; i < carrello.size(); i++){

            ProdottoAcquisto pa = new ProdottoAcquisto();
            pa.setNomeAuto(carrello.get(i).getNomeAuto());
            pa.setCasaAuto(carrello.get(i).getCasaAuto());
            pa.setQuantita(carrello.get(i).getQuantita());
            pa.setPrezzoTotale(carrello.get(i).getPrezzoDouble());
            pa.setIdCliente(idCliente);
            prezzoTotale += pa.getPrezzoTotale();
            prodotti.add(pa);
        }

        // Formattazione prezzo totale in string

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
        String prezzoTot = currencyFormatter.format(prezzoTotale);

        request.setAttribute("prodotti",prodotti);
        request.setAttribute("prezzoTot",prezzoTot);
        request.setAttribute("diretto", 0);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/results/checkout.jsp");
        dispatcher.forward(request, response);
    }
}

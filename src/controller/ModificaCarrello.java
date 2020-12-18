package controller;

import model.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

// Servlet per modificare gli elementi all'interno del carrello: quantità, prezzo e anche la sua eliminazione
@WebServlet("/ModificaCarrello")
public class ModificaCarrello extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        String nomeAuto = request.getParameter("nomeAuto");
        String casaAuto = request.getParameter("casaAuto");

        int idCliente = (Integer) session.getAttribute("idCliente");

        /*
            Viene istanziato un oggetto ProdottoCarrelo, con nomeAuto e casaAuto ricevuti dalla richiesta AJAX e ID
            cliente presso dalla sessione
        */

        ProdottoCarrello pc = new ProdottoCarrello();
        pc.setIdCliente(idCliente);
        pc.setNomeAuto(nomeAuto);
        pc.setCasaAuto(casaAuto);


        ProdottoCarrelloDAO pcd = new ProdottoCarrelloDAO();

        /*
            Verifica se il bottone che è stato cliccato sia REMOVE, in caso affermativo cancella l'auto specifica dal
            carrello
        */
        if (request.getParameter("remove") != null && !request.getParameter("remove").equals("update")) //vedere che si tratti della rimozione
            pcd.doRemoveProdottoCarrello(pc);

        /*
            Altrimenti significa che l'evento è quello di aggiornare qualche campo
            si verifica questo evento quando viene cambiata la quantità di un auto nel carrello,
            si effettua l'aggiornamento nel DB
        */
        else {

            int quantita = Integer.parseInt(request.getParameter("quantita"));
            pc.setQuantita(quantita);
            pcd.doSaveProdottoCarrelloByIdNomeAutoCasaAuto2(idCliente,pc);
        }

        // Presa di tutte le auto presenti nel carrello

        List<ProdottoCarrello> carrello = pcd.doRetrieveCarrelloByIdCliente(idCliente);
        for(ProdottoCarrello ca : carrello)
            ca.setPath("./images/car_marche/", "", ".png");

        Double prezzoTotale = 0.0;

        // Calcolo del prezzo totale degli elementi presenti nel carrello

        for(int i = 0; i < carrello.size(); i++)
            prezzoTotale += carrello.get(i).getPrezzoDouble();

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
        String prezzoTot = currencyFormatter.format(prezzoTotale);

        request.setAttribute("carrello",carrello);
        request.setAttribute("prezzoTotaleString",prezzoTot);

        // Creazione risposta (di tipo XML)

        response.setContentType("text/xml;charset=UTF-8");
        /*creazione del documento XML:
            <items>
                <item>
                    <path></path>
                    <nomeAuto></nomeAuto>
                    <casaAuto></casaAuto>
                    <prezzo></prezzo>
                    <quantita></quantita>
                 </item>
                 <item>
                    <prezzoTotale></prezzoTotale>
                 </item>
             </items>
        */
        response.getWriter().append("<items>");

        for (int j = 0; j < carrello.size(); j++) {

            String append = "<item><path>" + carrello.get(j).getPath() +
                    "</path><nomeAuto>" + carrello.get(j).getNomeAuto() +
                    "</nomeAuto><casaAuto>" + carrello.get(j).getCasaAuto() +
                    "</casaAuto><prezzo>" + carrello.get(j).getPrezzo() +
                    "</prezzo><quantita>" + carrello.get(j).getQuantita() +
                    "</quantita></item>";

            response.getWriter().append(append);
        }

        String item = "<item><prezzoTotale>" + prezzoTot + "</prezzoTotale></item>";
        response.getWriter().append(item);

        response.getWriter().append("</items>");
    }
}

package controller;

import model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

// Servlet che effettua l'acquisto delle auto diretto o dal carrello in entrambi i casi prima passando per il check out
@WebServlet("/FinalizzaOrder")
public class FinalizzaAcquistoCarrello extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Prelievo dei parametri dalla request

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String nazione = request.getParameter("nazione");
        String indirizzo = request.getParameter("indirizzo");
        String indUlteriore = request.getParameter("indUlteriore");
        String city = request.getParameter("city");
        String provincia = request.getParameter("provincia");
        String cap = request.getParameter("cap");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String carta = request.getParameter("carta");
        String intestatario = request.getParameter("intestatario");
        String cvc = request.getParameter("cvc");
        int year = Integer.parseInt(request.getParameter("year_scadenza"));
        int mese = Integer.parseInt(request.getParameter("selection_scadenza"));
        int diretto = Integer.parseInt(request.getParameter("diretto"));
        carta = carta.replaceAll(" ", "");
        boolean nomeOK = false, cognomeOK = false, nazioneOK = false, indirizzoOK = false, indUlterioreOK = false,
                cityOK = false, provinciaOK = false, capOK = false, telefonoOK = false, emailOK = false,
                cartaOK = false, intestatarioOK = false, cvcOK = false, yearOK = false, meseOK = false;

        // Controllo dei parametri che sono stati inviati

        if (nome.matches("^[a-zA-Z ']+$"))
            nomeOK = true;
        if (cognome.matches("^[a-zA-Z ']+$"))
            cognomeOK = true;
        if (!nazione.equalsIgnoreCase("Seleziona una nazione..."))
            nazioneOK = true;
        if (indirizzo.matches("^[a-zA-Z ']+$"))
            indirizzoOK = true;
        if (indUlteriore.matches("^[0-9a-zA-Z. ]+$"))
            indUlterioreOK = true;
        if (city.matches("^[a-zA-Z ']+$"))
            cityOK = true;
        if (provincia.matches("^[a-zA-Z ']+$"))
            provinciaOK = true;
        if (cap.trim().length() == 5 && cap.matches("^[0-9]+$"))
            capOK = true;
        if (telefono.trim().length() == 14 && telefono.matches("^\\+[0-9]{2} [0-9]+$"))
            telefonoOK = true;
        if (email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$"))
            emailOK = true;
        if ((carta.trim().length() == 16 && carta.trim().matches("^[5][1-5]{3}[0-9]{12}")) ||
                (carta.trim().length() == 16 && carta.trim().matches("^[4][04589][0124][345678][0-9]{12}")) ||
                (carta.trim().length() == 15 && carta.trim().matches("^[3][47][0-9]{13}"))) {
            cartaOK = true;
        }

        if (intestatario.matches("^[a-zA-Z ']+$"))
            intestatarioOK = true;
        if (cvc.trim().length() == 3 && cvc.matches("^[0-9]+$"))
            cvcOK = true;
        if (year >= 2020 && year <= 2100)
            yearOK = true;
        if (mese >= 1 && mese <= 12)
            meseOK = true;

        // Se le variabili di controllo vanno tutte bene allora si procede

        if (nomeOK && cognomeOK && nazioneOK && indirizzoOK && indUlterioreOK && cityOK && provinciaOK && capOK &&
                telefonoOK && emailOK && cartaOK && intestatarioOK && cvcOK && yearOK && meseOK) {

            HttpSession session = request.getSession();
            int idCliente = (Integer) session.getAttribute("idCliente");
            ProdottoAcquistoDAO pad = new ProdottoAcquistoDAO();

            // if nel caso in cui l'acquisto è diretto, senza passare per il carrello, variabile settata in ServletAcquisto

            if (diretto == 1) {

                List<ProdottoAcquisto> prodotti = (List<ProdottoAcquisto>) session.getAttribute("prodotti");

                for (int i = 0; i < prodotti.size(); i++)
                    pad.doSaveProdottoAcquisto(prodotti.get(i));

                session.setAttribute("prodotti", null);

                // Settaggio messaggi di acquisto riuscito

                request.setAttribute("customer", prodotti);
                request.setAttribute("text_error", " ");
                request.setAttribute("text_ok", "Acquisto effettuato correttamente.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/resultRegistration.jsp");
                dispatcher.forward(request, response);

            }

            else {

                // Acquisto delle auto presenti nel carrello

                ProdottoCarrelloDAO pcd = new ProdottoCarrelloDAO();
                List<ProdottoCarrello> carrello = pcd.doRetrieveCarrelloByIdCliente(idCliente);
                List<ProdottoAcquisto> prodotti = new ArrayList<>();

                // Creazione della data di acquisto

                GregorianCalendar calendar = new GregorianCalendar();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String DataAcquisto = format.format(calendar.getTime());

                // Creazione degli oggetti prodotto acquisto e loro inserimento in un array

                ProdottoAcquisto pa;
                for (int i = 0; i < carrello.size(); i++) {

                    pa = new ProdottoAcquisto();
                    pa.setNomeAuto(carrello.get(i).getNomeAuto());
                    pa.setCasaAuto(carrello.get(i).getCasaAuto());
                    pa.setQuantita(carrello.get(i).getQuantita());
                    pa.setPrezzoTotale(carrello.get(i).getPrezzoDouble());
                    pa.setIdCliente(idCliente);
                    pa.setData(DataAcquisto);
                    prodotti.add(pa);
                }


                // Salvataggio delle auto acquistate ed eliminazione delle auto dal carrello

                for (int i = 0; i < prodotti.size(); i++)
                    pad.doSaveProdottoAcquistoAndDelete(prodotti.get(i));

                request.setAttribute("customer", prodotti);
                request.setAttribute("text_error", " ");
                request.setAttribute("text_ok", "Acquisto effettuato correttamente.");

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/resultRegistration.jsp");
                dispatcher.forward(request, response);
            }
        }

        else {

            // L'acquisto non è avvenuto

            request.setAttribute("text_error", "Acquisto non effettuato correttamente");

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/resultRegistration.jsp");
            dispatcher.forward(request, response);
        }
    }
}

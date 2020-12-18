package controller;

import model.*;

import java.text.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

// Servlet per effettuare il login
@WebServlet("/login")
public class Login extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Creazione del bean Customer
        CustomerDAO cd = new CustomerDAO();
        Customer c = cd.doRetrieveByUsernamePassword(username, password);

        RequestDispatcher dispatcher;
        //verifica se il customer esiste nel DB, quindi diverso da null
        if (c != null) {

            /*
                Se colui che accede è un amministratore allora vengono caricati tutti i clienti e tutte le auto
                presenti nel database
            */
            if (c.isAmministratore()) {

                HttpSession session = request.getSession(false);

                CarDAO carD = new CarDAO();
                CustomerDAO custDAO = new CustomerDAO();

                List<Car> listaAuto = carD.doRetrieveAllCars();
                List<Customer> listaClienti = custDAO.doRetrieveAllCustomers();

                /*
                    Salvataggio dell'id dell'amministratore, della lista delle auto e della lista dei
                    clienti nella sessione
                */
                session.setAttribute("idAmministratore", c.getId());
                session.setAttribute("listaAuto", listaAuto);
                session.setAttribute("listaClienti", listaClienti);

                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/results/amministratore.jsp");
                dispatcher.forward(request, response);
            }

            else {

                /*
                    Qui devono essere caricati dal server tutte le macchine che lui ha nei preferiti e nel carrello
                    e tutti gli acquisti effettuati
                */

                HttpSession session = request.getSession(true);

                ProdottoPreferitoDAO pfd = new ProdottoPreferitoDAO();
                ProdottoCarrelloDAO pcd = new ProdottoCarrelloDAO();
                ProdottoAcquistoDAO pad = new ProdottoAcquistoDAO();

                /*
                    Inserisce i preferiti che l'utente ha selezionato prima di fare
                    il login e che ora sono nella sessione
                */
                pfd.doSaveProdottoPreferitoByIdClienteNomeAutoCasaAuto(c.getId(),
                        (List<ProdottoPreferito>) session.getAttribute("preferitiDaSalvare"));
                session.setAttribute("preferitiDaSalvare", null);

                /*
                    Inserisce gli articoli che sono stati selezionati per essere inseriti
                    nel carrello prima di fare il login e che ora si trovano nella sessione
                */
                pcd.doSaveProdottoCarrelloByIdNomeAutoCasaAuto(c.getId(),
                        (List<ProdottoCarrello>) session.getAttribute("carrelloDaSalvare"));
                session.setAttribute("carrelloDaSalvare", null);

                List<ProdottoPreferito> preferiti = pfd.doRetrievePreferitiByIdCliente(c.getId());
                List<ProdottoCarrello> carrello = pcd.doRetrieveCarrelloByIdCliente(c.getId());
                List<ProdottoAcquisto> acquisti = pad.doRetrieveAcquistiByIdCliente(c.getId());

                Double prezzoTotale = 0.0;

                for (ProdottoCarrello pc : carrello)
                    pc.setPath("./images/car_marche/", "", ".png");
                for (ProdottoAcquisto pc : acquisti)
                    pc.setPath("./images/car_marche/", "", ".png");
                for (ProdottoPreferito pc : preferiti)
                    pc.setPath("./images/car_marche/", "", ".png");

                // Calcolo del prezzo totale del carrello

                for(int i = 0; i < carrello.size(); i++)
                    prezzoTotale += carrello.get(i).getPrezzoDouble();

                // Formattazione dei prezzo totale

                NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
                String prezzoTot = currencyFormatter.format(prezzoTotale);

                // Salvataggio degli attributi nella request

                request.setAttribute("prezzoTotale", prezzoTot);
                request.setAttribute("acquisti", acquisti);
                request.setAttribute("preferiti", preferiti);
                request.setAttribute("customer", c);
                session.setAttribute("idCliente", c.getId());
                session.setAttribute("preferiti", preferiti);
                session.setAttribute("carrello", carrello);
                session.setAttribute("acquisti", acquisti);

                int acquistoProssimo = Integer.parseInt(request.getParameter("acquistoProssimo"));
                int accessoCarrello = Integer.parseInt(request.getParameter("accessoCarrello"));

                // Se vuole fare un acquisto, acquisto prossimo sarà posto ad un valore diverso da 0, settato in ServletAcquisto
                if (acquistoProssimo != 0) {

                    request.setAttribute("acquistoProssimo", 0); //viene annullato l'attributo

                    String nomeAuto = request.getParameter("nomeAuto");
                    String marcaAuto = request.getParameter("casaAuto");

                    CarDAO carDao = new CarDAO();
                    Car car = carDao.doRetrieveAutoByNomeCasaAuto(nomeAuto, marcaAuto);

                    ProdottoAcquisto auto = new ProdottoAcquisto();
                    //settaggio data acquisto
                    GregorianCalendar calendar = new GregorianCalendar();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String DataAcquisto = format.format(calendar.getTime());

                    auto.setNomeAuto(nomeAuto);
                    auto.setCasaAuto(marcaAuto);
                    auto.setQuantita(1);
                    auto.setPrezzoTotale(car.getPrezzoDouble());
                    auto.setIdCliente(c.getId());
                    auto.setData(DataAcquisto);

                    List<ProdottoAcquisto> prodotti = new ArrayList<>();
                    prodotti.add(auto);
                    request.setAttribute("prodotti", prodotti);
                    request.setAttribute("prezzoTot", auto.getPrezzoTotaleString());
                    session.setAttribute("prodotti", prodotti);
                    request.setAttribute("diretto", 1); //attibuto che servirà in FinalizzaAcquistoCarrello per dire che si tratti di quello diretto

                    dispatcher = request.getRequestDispatcher("/WEB-INF/results/checkout.jsp");
                    dispatcher.forward(request, response);
                }

                // Se l'utente vuole effettuare l'accesso al carrello direttamente, settato nella ServletAccessoCarrello
                else if (accessoCarrello != 0) {

                    request.setAttribute("accessoCarrello", 0);

                    dispatcher = request.getRequestDispatcher("/WEB-INF/results/carrello.jsp");
                    dispatcher.forward(request, response);
                }

                /* Se tutti i precedenti valori sono settati a zero, significa che è stata cliccata l'icona per andare nell'area personale */
                else {

                    dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/results/userProfile.jsp");
                    dispatcher.forward(request, response);
                }
            }
        }

        // else nel caso in cui l'utente ha sbagliato i campi oppure non è presente nel DB; customer uguale a null
        else {
            //presa dei valori per l'acquisto diretto o accesso carrello dalla request, settati in ServletAcquisto e ServletAccessoCarrello
            int acquistoProssimo = Integer.parseInt(request.getParameter("acquistoProssimo"));
            int acquistoCarrello = Integer.parseInt(request.getParameter("accessoCarrello"));
            int errorLog = 1;

            ProdottoAcquisto auto = new ProdottoAcquisto();
            auto.setNomeAuto(request.getParameter("nomeAuto"));
            auto.setCasaAuto(request.getParameter("casaAuto"));

            request.setAttribute("acquistoProssimo", acquistoProssimo);
            request.setAttribute("accessoCarrello", acquistoCarrello);
            request.setAttribute("auto", auto);
            request.setAttribute("errorLog",errorLog);

            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/results/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}

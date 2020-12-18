package controller;

import model.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

// Servlet per gestire i filtri in show-auto-marca.jsp
@WebServlet("/Filtri")
public class XMLFiltri extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        CarDAO cardao = new CarDAO();
        List<String> marcheAuto = (List<String>) getServletContext().getAttribute("marcheAuto");  //ottiene le marche di auto dal ServletContext
        int i;
        String casaAuto = "";

        if(request.getParameter("casaAuto").equals("AllCars")) //vedere se è stato cliccato il bottone per vedere tutte le auto
            casaAuto = request.getParameter("casaAuto");

        else if(request.getParameter("casaAuto")!= null) {
            for (i = 0; i < marcheAuto.size(); i++) {
                if (request.getParameter("casaAuto").equals(marcheAuto.get(i))) {
                    casaAuto = marcheAuto.get(i);  //presa la marca auto adatta, ovvero quella specifica nella request
                    break;
                }
            }
        }

        // Settaggio di valori di default (per la query finale) se non stati riempiti i campi
        String categoria = null;
        Double PrezzoMin = 0.0;
        Double PrezzoMax = 10000000000.0;
        Integer velocitaMin = 0;
        Integer velocitaMax = 800;
        String orderbyprezzo = "nome ASC";

        // Verifica quali campi sono stati settati; Valore di default "error" per ogni campo;
        if (!request.getParameter("categoria").equals("error"))
            categoria = request.getParameter("categoria");

        if (!request.getParameter("prezzoMin").equals("error"))
            PrezzoMin = Double.parseDouble(request.getParameter("prezzoMin"));

        if (!request.getParameter("prezzoMax").equals("error"))
            PrezzoMax = Double.parseDouble(request.getParameter("prezzoMax"));

        if (!request.getParameter("velocitaMin").equals("error"))
            velocitaMin = Integer.parseInt(request.getParameter("velocitaMin"));

        if (!request.getParameter("velocitaMax").equals("error"))
            velocitaMax = Integer.parseInt(request.getParameter("velocitaMax"));

        if (request.getParameter("ordinePrezzo").equals("ordinePrezzoCrescente"))
            orderbyprezzo = "prezzo ASC";

        else if (request.getParameter("ordinePrezzo").equals("ordinePrezzoDecrescente"))
            orderbyprezzo = "prezzo DESC";

        // Presa delle auto dal DB in base ai filtri settati
        List<Car> auto = cardao.retrieveAutoFilter(casaAuto, categoria, PrezzoMin, PrezzoMax, velocitaMin, velocitaMax,
                orderbyprezzo);

        for (Car a : auto)
            a.setPath("./images/car_marche/", "", ".png");

        // Settaggio risposta (tipo XML)
        response.setContentType("text/xml;charset=UTF-8");
        /*creazione documento XML:
            <cars>
                <auto>
                    <path></path>
                    <nome></nome>
                    <casaAuto></casaAuto>
                    <velocitaMax></velocitaMax>
                    <categoria></categoria>
                    <prezzo></prezzo>
               </auto>
            </cars>
         */
        response.getWriter().append("<cars>");
        for (int j = 0; j < auto.size(); j++) {

            String velmax = String.valueOf(auto.get(j).getVelMax());
            String append = "<auto><path>" + auto.get(j).getPath() +
                    "</path><nome>" + auto.get(j).getNome() +
                    "</nome><casaAuto>" + auto.get(j).getCasaAuto() +
                    "</casaAuto><velocitaMax>" + velmax +
                    "</velocitaMax><categoria>" + auto.get(j).getCategoria() +
                    "</categoria><prezzo>" + auto.get(j).getPrezzo() +
                    "</prezzo></auto>";

            response.getWriter().append(append);
        }

        response.getWriter().append("</cars>");
    }
}

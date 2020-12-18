package controller;

import model.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

// Servlet per eliminare auto dai preferiti
@WebServlet("/modifica-preferiti")
public class ModificaPreferiti  extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        String nomeAuto = request.getParameter("nomeAuto");
        String casaAuto = request.getParameter("casaAuto");

        int idCliente = (Integer) session.getAttribute("idCliente");

        /*
            viene istanziato un oggetto ProdottoPreferito con id preso dalla sessione e i parametri nomeAuto, casaAuto
            ricevuti dalla richiesta AJAX
        */
        ProdottoPreferito pf = new ProdottoPreferito();
        pf.setIdCliente(idCliente);
        pf.setNomeAuto(nomeAuto);
        pf.setCasaAuto(casaAuto);

        ProdottoPreferitoDAO ppdao = new ProdottoPreferitoDAO();

        // Rimozione auto con nomeAuto e casaAuto che sono stati ricevuti

        if (request.getParameter("remove") != null)
            ppdao.doRemoveProdottoPreferito(pf);

        // Acquisizione di tutte le auto presenti nei preferiti (salvate nel DB)

        List<ProdottoPreferito> preferito = ppdao.doRetrievePreferitiByIdCliente(idCliente);
        for(ProdottoPreferito ca : preferito)
            ca.setPath("./images/car_marche/", "", ".png");

        request.setAttribute("preferiti",preferito);

        // Settaggio della risposta (di tipo XML)

        response.setContentType("text/xml;charset=UTF-8");
        /*creazione documento XML:
         <items>
            <item>
               <path></path>
               <nomeAuto></nomeAuto>
               <casaAuto></casaAuto>
            </item>
           </items>
          */
        response.getWriter().append("<items>");

        for (int j = 0; j < preferito.size(); j++) {

            String append = "<item><path>" + preferito.get(j).getPath() +
                    "</path><nomeAuto>" + preferito.get(j).getNomeAuto() +
                    "</nomeAuto><casaAuto>" + preferito.get(j).getCasaAuto() +
                    "</casaAuto></item>";

            response.getWriter().append(append);
        }

        response.getWriter().append("</items>");

    }
}

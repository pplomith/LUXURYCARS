package controller;

import model.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/ServletPreferito")
public class ServletPreferito extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String nomeAuto = request.getParameter("nomeAuto");
        String casaAuto = request.getParameter("casaAuto");

        HttpSession session = request.getSession(false);
        //settaggio tipo rsposta (in questo caso xml)
        response.setContentType("text/xml;charset=UTF-8");

        /* Se il cliente ancora non ha fatto l'accesso, vengono salvati i preferiti nella sessione */
        if (session.getAttribute("idCliente") == null) {

            CarDAO cd = new CarDAO();
            Car auto = cd.doRetrieveAutoByNomeCasaAuto(nomeAuto, casaAuto);
            List<ProdottoPreferito> preferitiDaSalvare = (List<ProdottoPreferito>)session.getAttribute("preferitiDaSalvare");

            ProdottoPreferito autoPreferita = new ProdottoPreferito();
            autoPreferita.setNomeAuto(auto.getNome());
            autoPreferita.setCasaAuto(auto.getCasaAuto());
            preferitiDaSalvare.add(autoPreferita);

            session.setAttribute("preferitiDaSalvare", preferitiDaSalvare);

            response.getWriter().append("<okAccesso/>");
        }
        //altrimenti significa che ha fatto l'accesso e il prodotto preferito può essere salvato nel database
        else {

            ProdottoPreferitoDAO pfd = new ProdottoPreferitoDAO();
            int idCliente = (int)session.getAttribute("idCliente"); //presa dell'id del cliente dalla sessione

            ProdottoPreferito pf = new ProdottoPreferito();
            pf.setNomeAuto(nomeAuto);
            pf.setCasaAuto(casaAuto);

            if (pfd.doSaveProdottoPreferitoByIdClienteNomeAutoCasaAuto1(idCliente, pf))
                response.getWriter().append("<ok/>");
            else
                response.getWriter().append("<no/>");
        }
    }
}

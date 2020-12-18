package controller;

import model.*;

// import del package org.json.simple.* per gestire in maniera semplificata gli oggetti
import org.json.simple.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

// Servlet che permette di visualizzare gli ordini di un cliente da parte dell'amministratore
@WebServlet("/OrdiniCliente")
public class OrdiniCliente extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Settaggio della respinse a text/plain quindi formato che accetta JSON
        response.setContentType("text/plain;charset=UTF-8");

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        // Prelievo di tutti gli acquisti dal DB per quel cliente
        ProdottoAcquistoDAO cd = new ProdottoAcquistoDAO();
        List<ProdottoAcquisto> prodottiAcquisto = cd.doRetrieveAcquistiByIdCliente(idCliente);

        JSONObject auto;
        JSONArray listaProdotti = new JSONArray();

        // Creazione dell'array che contiene gli ordini del cliente
        for (ProdottoAcquisto p : prodottiAcquisto) {
            auto = new JSONObject();
            auto.put("idOrdine", p.getIdAcquisto());
            auto.put("idCustomer", p.getIdCliente());
            auto.put("nomeAuto", p.getNomeAuto());
            auto.put("prezzo", p.getPrezzoTotaleString());
            listaProdotti.add(auto);
        }

        // Scrittura della risposta nella response
        String clientiText = JSONValue.toJSONString(listaProdotti);
        response.getWriter().append(clientiText);
    }
}

package controller;

import model.*;

// Import del package org.json.simple.* per utilizzo facilitato di JSON
import org.json.simple.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

// Servlet per rimuovere il cliente
@WebServlet("/RimuoviCliente")
public class RimuoviCliente extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException{

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Settaggio tipo risposta
        response.setContentType("text/plain;charset=UTF-8");

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Customer c = new Customer();
        c.setId(idCliente);

        // Prelievo di tutti i customer dal database
        CustomerDAO cd = new CustomerDAO();
        cd.doRemoveCustomer(c);
        List<Customer> customer = cd.doRetrieveAllCustomers();

        JSONObject cliente;
        JSONArray clienti = new JSONArray();

        // Creazione degli oggetti e aggiunta all'array
        for (Customer c1 : customer) {
            cliente = new JSONObject();
            cliente.put("idCustomer", c1.getId());
            cliente.put("nome", c1.getNome());
            cliente.put("cognome", c1.getCognome());
            clienti.add(cliente);
        }

        // Scrittura della risposta in response
        String clientiText = JSONValue.toJSONString(clienti);
        response.getWriter().append(clientiText);
    }
}

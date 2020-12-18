package controller;

import model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

// Servlet per effettuare la registrazione
@WebServlet("/registration")
public class Registration extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Acquisizione dei campi inseriti

        String username = request.getParameter("username");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean usernameeValid = false, nomeValid = false, cognomeValid = false, emailValid = false,
                passwordValid = false;

        //Validazione dei campi inseriti; Se è valido: boolean: true; altrimenti boolean: false;

        if ((username != null && username.length() >= 6 && username.matches("^[0-9a-zA-Z]+.+$"))){
            CustomerDAO cda = new CustomerDAO();
            if (cda.doRetrieveByUsername(username) == null) {
                usernameeValid = true;
            }
        }

        if ((password != null && password.length() >= 8 && !password.toUpperCase().equals(password)
                && !password.toLowerCase().equals(password) && password.matches("^[A-Z]+.*[0-9].*")))
            passwordValid = true;

        if ((nome != null && nome.trim().length() > 0 && nome.matches("^[ a-zA-Z\u00C0-\u00ff']+$")))
            nomeValid = true;

        if ((cognome != null && cognome.trim().length() > 0 && cognome.matches("^[ a-zA-Z\u00C0-\u00ff']+$")))
            cognomeValid = true;

        if ((email != null && email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")))
            emailValid = true;

        /*
            Verifico se tutti i campi sono corretti, se si allora
            lancia ResultRegistration con esito positivo, altrimenti con esito negativo
         */

        if(passwordValid && nomeValid && cognomeValid && emailValid && cognomeValid && usernameeValid) {
            
            var service = new CustomerDAO();

            Customer customer = new Customer();
            customer.setUsername(username);
            customer.setNome(nome);
            customer.setCognome(cognome);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setAmministratore(false);

            /*
                Settaggi per dire a resultRegistration se la registrazione è andata a buon fine oppure no;
                Registrazione effettuata
            */

            request.setAttribute("customer", customer);
            request.setAttribute("text_error"," "); //messaggio di errore vuoto
            request.setAttribute("text_ok","La registrazione è stata completata correttamente. " +
                    "Effettua il login."); //messaggio di conferma
            service.doRegisterCustomer(customer);
            String address = "/WEB-INF/results/resultRegistration.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

        else {

            /*
                Settaggi per dire a resultRegistration se la registrazione è andata a buon fine oppure no
                Registrazione fallita
            */

            request.setAttribute("text_ok"," "); //messaggio di conferma vuoto
            request.setAttribute("text_error","Registrazione fallita, campi errati."); //messaggio di errore
            request.setAttribute("customer", null);
            String address = "/WEB-INF/results/resultRegistration.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }
}

package controller;

import model.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


// Modifica dei dati da parte dell'utente
@WebServlet("/modifica-dati")
public class ModificaDati extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        RequestDispatcher dispatcher;
        CustomerDAO cdao = new CustomerDAO();
        int c = (Integer) session.getAttribute("idCliente");

        // Verifica sei il parametro "modifica_dati" è nullo
        if (request.getParameter("modifica_dati") != null){

            if (request.getParameter("modifica_dati").equals("Modifica profilo")) {

                // Prelievo dei parametri inviati con la request
                String nome = request.getParameter("fname");
                String cognome = request.getParameter("lname");
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String oldPassword = request.getParameter("oldpw");
                String newPassword = request.getParameter("newpw");

                boolean oldPw = false, newPw = false, usernameValid = true, nomeValid = false,
                        cognomeValid = false, emailValid = false;

                Customer cupdate = new Customer();
                cupdate.setId(c);
                cupdate.setPassword(oldPassword);

                if (oldPassword.equals("") || newPassword.equals("")) {
                    oldPw = true;
                    newPw = true;
                }

                else if (cdao.CheckPassword(cupdate)) {

                    oldPw = true;
                    if ((newPassword != null && newPassword.length() >= 8
                            && !newPassword.toUpperCase().equals(newPassword)
                            && !newPassword.toLowerCase().equals(newPassword)
                            && newPassword.matches("^[A-Z]+.*[0-9].*"))) {

                        newPw = true;
                        cupdate.setPassword(newPassword);
                        cdao.doUpdatePassword(cupdate);
                    }
                }

                // Verifica dei parametri

                if ((username != null && username.length() >= 6 && username.matches("^[0-9a-zA-Z]+.+$"))) {
                    if (cdao.doRetrieveByUsername(username) == null)
                        usernameValid = true;
                }

                if ((nome != null && nome.trim().length() > 0 && nome.matches("^[ a-zA-Z\u00C0-\u00ff']+$")))
                    nomeValid = true;

                if ((cognome != null && cognome.trim().length() > 0
                        && cognome.matches("^[ a-zA-Z\u00C0-\u00ff']+$")))
                    cognomeValid = true;

                if ((email != null && email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")))
                    emailValid = true;

                // Se tutte le variabili sono true allora la modifica viene completata

                if (oldPw && newPw && usernameValid && nomeValid && cognomeValid && emailValid) {

                    cupdate.setNome(nome);
                    cupdate.setCognome(cognome);
                    cupdate.setUsername(username);
                    cupdate.setEmail(email);
                    cdao.doUpdateDati(cupdate);
                    request.setAttribute("customer", cupdate);
                    request.setAttribute("text_error", " ");
                    request.setAttribute("text_ok", "La modifica dei dati è stata completata correttamente.");
                    String address = "/WEB-INF/results/resultRegistration.jsp";
                    dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                }

                // Altrimenti la modifica fallisce

                else {

                    request.setAttribute("text_ok", " ");
                    request.setAttribute("text_error", "Modifica fallita, campi errati.");
                    request.setAttribute("customer", null);
                    String address = "/WEB-INF/results/resultRegistration.jsp";
                    dispatcher = request.getRequestDispatcher(address);
                    dispatcher.forward(request, response);
                }
            }
        }
    }
}

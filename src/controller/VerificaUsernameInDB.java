package controller;

import model.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


// Servlet per verificare nel DB se lo username è già in uso
@WebServlet("/VerificaUsername")
public class VerificaUsernameInDB extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        response.setContentType("text/xml");

        final CustomerDAO cdao = new CustomerDAO();

        // se l'ultima condizione è uguale a null significa che lo username inserito è disponibile (non presente nel DB)
        if(username != null && username.length() >= 6 && username.matches("^[0-9a-zA-Z]+.+$") &&
                cdao.doRetrieveByUsername(username) == null)
            response.getWriter().append("<ok/>");
        else
            response.getWriter().append("<no/>");
    }
}

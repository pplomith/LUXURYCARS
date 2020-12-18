package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

// Servlet per eseguire il logout dell'amministratore
@WebServlet("/LogOutAmministratore")
public class LogOutAmministratore extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // Settaggio degli attributi relativi all'amministratore a null: id, listaAuto, listaClienti

        HttpSession session = request.getSession();
        session.setAttribute("idAmministratore", null);
        session.setAttribute("listaAuto", null);
        session.setAttribute("listaClienti", null);

        // Reindirizzamento all'index

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.html");
        dispatcher.forward(request, response);
    }
}

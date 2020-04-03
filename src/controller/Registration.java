package controller;

import model.Customer;
import model.CustomerDAO;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        var service = new CustomerDAO();

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setNome(nome);
        customer.setCognome(cognome);
        customer.setEmail(email);
        customer.setPassword(password);

        request.setAttribute("customer", customer);

        try {

            service.doSave(customer);
            String address = "/WEB-INF/results/registration-accepted.jsp";

            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}

package controller;

import model.*;

import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show-auto-marca")
public class MostraAutoMarca extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> marcheAuto = (List<String>)getServletContext().getAttribute("marcheAuto");

        CarDAO carDAO = new CarDAO();

        int i;
        for (i = 0; i < marcheAuto.size(); i++) {
            if (request.getParameter(marcheAuto.get(i)) != null)
                break;
        }

        List<Car> auto = carDAO.retrieveAutoMarca(marcheAuto.get(i));

        request.setAttribute("marcaAuto", marcheAuto.get(i));
        request.setAttribute("auto", auto);

        String address = "/WEB-INF/results/show-auto-marca.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);

        dispatcher.forward(request, response);
    }
}

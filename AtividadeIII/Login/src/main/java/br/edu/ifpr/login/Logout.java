package br.edu.ifpr.login;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name= "logout", value="/logout")

public class Logout extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        ServletContext context = getServletContext();
        context.setAttribute("logado", false);
        System.out.println("Desconectado");
        RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}

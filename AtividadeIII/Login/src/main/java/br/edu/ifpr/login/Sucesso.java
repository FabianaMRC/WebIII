package br.edu.ifpr.login;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="sucesso", value="/sucesso")
public class Sucesso extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        login(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        login(request, response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Map<String, String> erros = new HashMap<>();
        ServletContext context = getServletContext();

        boolean logado = false;
        if (context.getAttribute("logado") == null){
            logado = false;
        } else{
            logado = (Boolean) context.getAttribute("logado");
        }

        if(logado){
            System.out.println("Login realizado com sucesso");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sucesso.jsp");
            dispatcher.forward(request, response);
        }else{
            System.out.println("Você não está logado");
            erros.put("erro_login", "Usuário não logado");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}

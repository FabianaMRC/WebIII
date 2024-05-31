package br.edu.ifpr.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RealizaLogin", value= "/login")

public class RealizaLogin extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map erros = new HashMap<String, String>();

        if(request.getParameter("usuario").isEmpty()){
            erros.put("erro_usuario", "Nome do usuário em branco");
        }else if(!request.getParameter("usuario").equals("webiii")){
            erros.put("erro_usuario", "Nome incorreto");
        }

        if(request.getParameter("senha").isEmpty()){
            erros.put("erro_senha", "Senha não informada");
        } else if (!request.getParameter("senha").equals("lindinha")) {
            erros.put("erro_senha", "Senha inválida");
            
        }if(!erros.isEmpty()){
            request.setAttribute("valida_erros", erros);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            System.out.println("sucesso");
            getServletContext().setAttribute("logado", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/sucesso");
            dispatcher.forward(request, response);
        }
    }
}

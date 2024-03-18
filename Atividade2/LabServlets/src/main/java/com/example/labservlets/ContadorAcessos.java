package com.example.labservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Contador")

public class ContadorAcessos extends HttpServlet {

    private int contador;

    @Override

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException    {
        contador++;

        resp.getWriter().println("Esta página foi acessada: " + contador + " vezes.");

    }
}

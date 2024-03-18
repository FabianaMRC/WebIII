package com.example.labservlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Life_cycle")
public class LifeCycleServlet extends HttpServlet {

    private String message;

    public void init() throws ServletException{
        System.out.println("Invocou o método init");
    }
    @Override

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        System.out.println("Chamou o método Service/do Get");
    }


    public void destroy() {
            System.out.println("Chamou o método destroy");
        }
    }


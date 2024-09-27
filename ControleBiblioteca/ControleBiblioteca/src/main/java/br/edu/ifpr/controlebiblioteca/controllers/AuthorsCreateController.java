package br.edu.ifpr.controlebiblioteca.controllers;

import br.edu.ifpr.controlebiblioteca.models.Author;
import br.edu.ifpr.controlebiblioteca.repositories.AuthorRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;


import java.io.IOException;

@WebServlet("/authors/create")
public class AuthorsCreateController extends HttpServlet {

    private AuthorRepository authorRepository;

    public AuthorsCreateController() {
        this.authorRepository = new AuthorRepository();}

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/author.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorName = req.getParameter("author_name");

        Author author = new Author();
        author.setName(authorName);

        authorRepository.insert(author);

        resp.sendRedirect(req.getContextPath() + "/authors/list");
    }
}

package br.edu.ifpr.controlebiblioteca.controllers;


import br.edu.ifpr.controlebiblioteca.models.Author;
import br.edu.ifpr.controlebiblioteca.models.Book;
import br.edu.ifpr.controlebiblioteca.repositories.AuthorRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/authors/update")
public class AuthorUpdateController extends HttpServlet {
    AuthorRepository authorRepository = new AuthorRepository();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));

        List<Author> authors = authorRepository.getAll();

        req.setAttribute("authors", authors);

        Author author = authorRepository.getById(id);
        req.setAttribute("author", author);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/authors-update.jsp");

        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(req.getParameter("id"));
            String name = req.getParameter("name");

            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("O nome é obrigatório.");
            }

            Author author = new Author();
            author.setId(id);
            author.setName(name);

            authorRepository.updateAuthorName(id, name);
            resp.sendRedirect(req.getContextPath() + "/authors/list");

        } catch (Exception e) {
            req.setAttribute("error", "Erro ao atualizar o autor: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/authors-list.jsp");
            dispatcher.forward(req, resp);
        }
    }
}

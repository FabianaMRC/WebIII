package br.edu.ifpr.controlebiblioteca.controllers;

import br.edu.ifpr.controlebiblioteca.models.Author;
import br.edu.ifpr.controlebiblioteca.repositories.AuthorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/authors/delete")
public class AuthorsDeleteController extends HttpServlet {


        AuthorRepository repository = new AuthorRepository();

        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Integer id = Integer.valueOf(req.getParameter("id"));

            List<Author> authors = repository.getAll();
            req.setAttribute("authors", authors);

            if (repository.authorHasBooks(id)) {
                req.setAttribute("error", "Não é possível excluir este autor porque ele está vinculado a um ou mais livros.");
                req.getRequestDispatcher("/authors-list.jsp").forward(req, resp);

            } else {
                repository.delete(id);
                resp.sendRedirect("http://localhost:8080/ControleBiblioteca_war_exploded/authors/list");

            }


        }
}


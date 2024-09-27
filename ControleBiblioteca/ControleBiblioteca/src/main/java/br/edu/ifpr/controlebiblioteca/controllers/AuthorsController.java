package br.edu.ifpr.controlebiblioteca.controllers;
import br.edu.ifpr.controlebiblioteca.models.Author;
import br.edu.ifpr.controlebiblioteca.repositories.AuthorRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/authors/list")
public class AuthorsController extends HttpServlet {
        private AuthorRepository authorRepository;

        public AuthorsController() {
        authorRepository = new AuthorRepository();
    }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Author> authors = authorRepository.getAll();
            req.setAttribute("authors", authors);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/authors-list.jsp");
            dispatcher.forward(req, resp);
        }
}


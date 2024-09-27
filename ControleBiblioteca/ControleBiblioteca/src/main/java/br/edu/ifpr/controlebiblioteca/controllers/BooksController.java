package br.edu.ifpr.controlebiblioteca.controllers;

import br.edu.ifpr.controlebiblioteca.models.Book;
import br.edu.ifpr.controlebiblioteca.repositories.BookRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"", "/books"})

public class BooksController extends HttpServlet {
    private BookRepository repository;

        public BooksController() {
            repository = new BookRepository();
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

            List<Book> books = repository.getBooks();

            RequestDispatcher dispatcher = request.getRequestDispatcher("/books.jsp");
            request.setAttribute("books", books);

            dispatcher.forward(request, resp);
        }

    }
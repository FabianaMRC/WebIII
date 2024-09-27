package br.edu.ifpr.controlebiblioteca.controllers;

import br.edu.ifpr.controlebiblioteca.models.Book;
import br.edu.ifpr.controlebiblioteca.models.BookStatus;
import br.edu.ifpr.controlebiblioteca.repositories.BookRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/books/updateStatus")

public class BooksUpdateStatusController extends HttpServlet {
    BookRepository repository = new BookRepository();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Book book = repository.getById(id);
        req.setAttribute("book", book);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/books-update-status.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(req.getParameter("field_id"));

            String status = req.getParameter("field_status");

            BookStatus novoStatus = BookStatus.valueOf(status.toUpperCase());

            repository.updateStatus(id, novoStatus);

            resp.sendRedirect(req.getContextPath() + "/books");
        } catch (Exception e) {

            throw new ServletException("Erro ao atualizar o status do livro: " + e.getMessage());
        }
    }
}

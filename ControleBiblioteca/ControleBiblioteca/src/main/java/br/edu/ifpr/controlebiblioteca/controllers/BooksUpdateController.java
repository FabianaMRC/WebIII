package br.edu.ifpr.controlebiblioteca.controllers;

import br.edu.ifpr.controlebiblioteca.models.Author;
import br.edu.ifpr.controlebiblioteca.models.Book;
import br.edu.ifpr.controlebiblioteca.models.BookStatus;
import br.edu.ifpr.controlebiblioteca.repositories.AuthorRepository;
import br.edu.ifpr.controlebiblioteca.repositories.BookRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/books/update")
public class BooksUpdateController extends HttpServlet {
    BookRepository repository = new BookRepository();
    AuthorRepository authorRepository = new AuthorRepository();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));

        List<Author> authors = authorRepository.getAll();

        req.setAttribute("authors", authors);

        Book book = repository.getById(id);
        req.setAttribute("book", book);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/books-update.jsp");

        dispatcher.forward(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(req.getParameter("field_id"));
            String name = req.getParameter("field_name");
            LocalDate date = LocalDate.parse(req.getParameter("field_date"));
            String statusString = req.getParameter("field_status");
            BookStatus status = BookStatus.valueOf(statusString);
            Integer authorId = Integer.valueOf(req.getParameter("field_author"));

            if (name == null || authorId == null || status == null) {
                throw new IllegalArgumentException("É obrigatório o preenchimento de todos os campos.");
            }


            Author author = new Author();
            author.setId(authorId);

            Book book = new Book();

            book.setId(id);
            book.setName(name);
            book.setAuthor(author);
            book.setDate(date);
            book.setStatus(status);

            repository.update(book);
            resp.sendRedirect(req.getContextPath() + "/books");

        } catch (Exception e) {
            req.setAttribute("error", "Erro ao atualizar o livro: " + e.getMessage());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/books-update.jsp");
            dispatcher.forward(req, resp);
        }
    }
}

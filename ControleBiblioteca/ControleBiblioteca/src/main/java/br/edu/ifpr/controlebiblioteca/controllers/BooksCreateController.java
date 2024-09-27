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

@WebServlet("/books/create")

public class BooksCreateController extends HttpServlet {

    BookRepository repository = new BookRepository();
    AuthorRepository authorRepository = new AuthorRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Author> authors = authorRepository.getAll();

        if (authors != null && !authors.isEmpty()) {
            req.setAttribute("authors", authors);
        } else {
            req.setAttribute("authors", new java.util.ArrayList<Author>());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/books-create.jsp");
        dispatcher.forward(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String name = req.getParameter("field_name");
        String dateString = req.getParameter("field_date");
        String statusString = req.getParameter("field_status");
        String authorIdString =req.getParameter("field_author");

        String errorMessage = null;

        if (name == null || name.trim().isEmpty()) {
            errorMessage = "O campo nome é obrigatório.";
        }

        LocalDate date = null;

        if (dateString == null || dateString.trim().isEmpty()) {
            errorMessage = "O campo data de criação é obrigatório.";
        } else {
            try {
                date = LocalDate.parse(dateString);
            } catch (Exception e) {
                errorMessage = "Data inválida. O formato correto é AAAA-MM-DD.";
            }
        }

        BookStatus status = null;

        if (statusString == null || statusString.trim().isEmpty()) {
            errorMessage = "O campo status é obrigatório.";
        } else {
            try {
                status = BookStatus.valueOf(statusString);
            } catch (IllegalArgumentException e) {
                errorMessage = "Status inválido.";
            }
        }

        Integer authorId = null;
        if (authorIdString == null || authorIdString.trim().isEmpty()) {
            errorMessage = "O campo autor é obrigatório.";
        } else {
            try {
                authorId = Integer.valueOf(authorIdString);
            } catch (NumberFormatException e) {
                errorMessage = "Autor inválido.";
            }
        }

        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
            doGet(req, resp);
            return;
        }

        Author author = new Author();
        author.setId(authorId);

        Book book = new Book();

        book.setName(name);
        book.setAuthor(author);
        book.setDate(date);
        book.setStatus(status);

        repository.insert(book);
        resp.sendRedirect(req.getContextPath()+"/books");
    }
}

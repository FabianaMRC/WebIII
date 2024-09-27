package br.edu.ifpr.controlebiblioteca.models;

import br.edu.ifpr.controlebiblioteca.repositories.BookRepository;

import java.time.LocalDate;

public class Book {

    private Integer id;
    private String name;
    private Author author;
    private LocalDate date;
    private BookStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }


    public String toString() {
        return "Livro {" +
                "Id = " + id +
                ", título = '" + name + '\'' +
                ", autor = '" + (author != null ? author.getName() : "Unknown") + '\'' +
                ", data de criação = " + date +
                ", status = " + status +
                '}';
    }
}

package br.edu.ifpr.controlebiblioteca.repositories;

import br.edu.ifpr.controlebiblioteca.connection.ConnectionFactory;
import br.edu.ifpr.controlebiblioteca.exceptions.DataBaseException;
import br.edu.ifpr.controlebiblioteca.models.Book;
import br.edu.ifpr.controlebiblioteca.models.Author;
import br.edu.ifpr.controlebiblioteca.models.BookStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {

    private Connection connection;

    public BookRepository() {
        connection = ConnectionFactory.getConnection();
    }

    public List<Book> getBooks() {

        List<Book> books = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery("SELECT book.*, author.Name AS AuthorName  FROM book JOIN author ON book.AuthorId = author.Id");

            while (result.next()) {

                Author author = instantiateAuthor(result);
                Book book = instantiateBook(result, author);
                books.add(book);
            }

            result.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
           ConnectionFactory.closeConnection();
        }

        return books;
    }

    public Book insert(Book book) {
        String sql = "INSERT book (Name, Date, Status, AuthorId) " +
                "VALUES(?,?,?,?)";


        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, book.getName());
            statement.setDate(2, Date.valueOf(book.getDate()));
            statement.setString(3, book.getStatus().getStatus());
            statement.setInt(4, book.getAuthor().getId());

            Integer rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet id = statement.getGeneratedKeys();

                id.next();
                Integer bookId = id.getInt(1);
                System.out.println("Rows inserted: " + rowsInserted);
                System.out.println("Id: " + bookId);

                book.setId(bookId);
            }
            book.getId();

        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
        return book;
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM book WHERE Id =?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            Integer rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Row Deleted: " + rowsDeleted);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public Book getById(Integer id) {

        Book book;
        Author author;

        String sql = "SELECT book.*,author.Name as AuthorName FROM book INNER JOIN author ON book.AuthorId = author.Id WHERE book.Id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                author = this.instantiateAuthor(resultSet);
                book = this.instantiateBook(resultSet, author);

            } else {
                throw new DataBaseException("Livro não encontrado");
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        return book;
    }

    public List<Book> findByAuthor(int id) {
        List<Book> booksList = new ArrayList<>();

        String sql = "SELECT book.*, author.Name as AuthorName FROM book " +
                "INNER JOIN author ON book.AuthorId = author.Id " +
                "WHERE AuthorId = ? ORDER BY book.Name";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            Map<Integer, Author> map = new HashMap<>();

            while (resultSet.next()) {

                Author author = map.get(resultSet.getInt("AuthorId"));
                if (author == null) {
                    author = instantiateAuthor(resultSet);
                    map.put(resultSet.getInt("AuthorId"), author);
                }

                Book book = instantiateBook(resultSet, author);
                booksList.add(book);
            }

        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return booksList;
    }

    public Book instantiateBook(ResultSet resultSet, Author author) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("Id"));
        book.setName(resultSet.getString("Name"));
        book.setDate(resultSet.getDate("Date").toLocalDate());
        book.setStatus(BookStatus.valueOf(resultSet.getString("Status").toUpperCase()));
        book.setAuthor(author);

        return book;
    }

    public Author instantiateAuthor(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt("AuthorId"));
        author.setName(resultSet.getString("AuthorName"));

        return author;
    }

    public void update(Book book) {
        String sql = "UPDATE book SET " +
                "Name = ?, " +
                "Date = ?, " +
                "Status = ?, " +
                "AuthorId = ? " +
                "WHERE (book.Id = ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getName());
            statement.setDate(2, Date.valueOf(book.getDate()));
            statement.setString(3, book.getStatus().getStatus());
            statement.setInt(4, book.getAuthor().getId());
            statement.setInt(5, book.getId());

            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public void updateStatus(int bookId, BookStatus novoStatus) {
        String sql = "UPDATE book SET Status = ? WHERE Id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, novoStatus.getStatus());
            statement.setInt(2, bookId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Status do livro atualizado com sucesso no banco de dados.");
            } else {
                System.out.println("Nenhum livro encontrado com o ID fornecido.");
            }

        } catch (SQLException e) {
            throw new DataBaseException("Erro ao atualizar o status do livro: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
    }



}

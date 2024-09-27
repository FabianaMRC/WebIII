package br.edu.ifpr.controlebiblioteca.repositories;

import br.edu.ifpr.controlebiblioteca.connection.ConnectionFactory;
import br.edu.ifpr.controlebiblioteca.exceptions.DataBaseException;
import br.edu.ifpr.controlebiblioteca.models.Author;
import br.edu.ifpr.controlebiblioteca.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository {

    Connection connection;

    public AuthorRepository() {
        connection = ConnectionFactory.getConnection();
    }

    public Author insert(Author author) {
        String sql = "INSERT INTO author (Name) VALUES (?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, author.getName());

            Integer rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet id = statement.getGeneratedKeys();

                id.next();
                Integer authorId = id.getInt(1);
                System.out.println("Rows inserted: " + rowsInserted);
                System.out.println("Id: " + authorId);

                author.setId(authorId);
            }
            author.getId();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
        return author;
    }

    public List<Author> getAll() {

        String sql = "SELECT * FROM author";
        List<Author> authors = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                Author author = new Author();
                author.setId(result.getInt("Id"));
                author.setName(result.getString("Name"));
                authors.add(author);
            }

        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        return authors;
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM author WHERE Id =?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            Integer rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Row Deleted: " + rowsDeleted);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void updateAuthorName(Integer authorId, String newName) {
        String sql = "UPDATE author SET Name = ? WHERE Id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setInt(2, authorId);

            Integer rowsUpdate = statement.executeUpdate();
            if (rowsUpdate > 0) {
                System.out.println("Rows updated:" + rowsUpdate);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public boolean authorHasBooks(Integer authorId) {
        String sql = "SELECT COUNT(*) FROM book WHERE AuthorId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, authorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }

    public Author getById(Integer id) {

        Author author;

        String sql = "SELECT * FROM author WHERE Id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                author = new Author();
                author.setId(resultSet.getInt("Id"));
                author.setName(resultSet.getString("Name"));

            } else {
                throw new DataBaseException("Autor não encontrado");
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        return author;
    }



}


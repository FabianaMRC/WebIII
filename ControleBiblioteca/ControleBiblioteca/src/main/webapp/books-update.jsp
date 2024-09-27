<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.models.Book" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.models.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.models.BookStatus" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.utils.DateUtils" %>

<%
    Book book = (Book) request.getAttribute("book");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Atualização de Livro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
    <h1 class="mt-4">Atualizar Livro</h1>
    <form action="<%= request.getContextPath() %>/books/update" method="post">
        <input type="hidden" name="field_id" value="<%= book.getId() %>">

        <div class="mb-3">
            <label for="name" class="form-label">Nome:</label>
            <input type="text" class="form-control" id="name" name="field_name" value="<%= book.getName() %>" required>
        </div>

        <div class="mb-3">
            <label for="author" class="form-label">Autor:</label>
            <select class="form-select" id="author" name="field_author" required>
                <%
                    List<Author> authors = (List<Author>) request.getAttribute("authors");
                    if (authors != null && !authors.isEmpty()) {
                        for (Author author : authors) {
                %>
                <option value="<%= author.getId() %>" <%= book.getAuthor().getId() == author.getId() ? "selected" : "" %>>
                    <%= author.getName() %>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div class="mb-3">
            <label for="date" class="form-label">Data de Criação:</label>
            <input type="date" class="form-control" id="date" name="field_date" value="<%= DateUtils.localDateToBrazilianFormat(book.getDate()) %>" required>
        </div>

        <div class="mb-3">
            <label for="status" class="form-label">Status:</label>
            <select class="form-select" id="status" name="field_status">
                <option value="DISPONIVEL" <%= request.getAttribute("book") != null && ((Book)request.getAttribute("book")).getStatus().equals(BookStatus.DISPONIVEL) ? "selected" : "" %>>Disponível</option>
                <option value="EMPRESTADO" <%= request.getAttribute("book") != null && ((Book)request.getAttribute("book")).getStatus().equals(BookStatus.EMPRESTADO) ? "selected" : "" %>>Emprestado</option>
                <option value="INDISPONIVEL" <%= request.getAttribute("book") != null && ((Book)request.getAttribute("book")).getStatus().equals(BookStatus.INDISPONIVEL) ? "selected" : "" %>>Indisponível</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Atualizar</button>
    </form>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.models.Book" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.models.BookStatus" %>

<%
    Book book = (Book) request.getAttribute("book");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alterar Status do Livro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
    <h2 class="my-4">Alterar Status do Livro</h2>

    <form action="<%= request.getContextPath() %>/books/updateStatus" method="post">
        <input type="hidden" name="field_id" value="<%= book.getId() %>">

        <div class="mb-3">
            <label for="status" class="form-label">Novo Status:</label>
            <select name="field_status" id="status" class="form-select">
                <option value="DISPONIVEL" <%= book.getStatus() == BookStatus.DISPONIVEL ? "selected" : "" %>>Disponível</option>
                <option value="EMPRESTADO" <%= book.getStatus() == BookStatus.EMPRESTADO ? "selected" : "" %>>Emprestado</option>
                <option value="INDISPONIVEL" <%= book.getStatus() == BookStatus.INDISPONIVEL ? "selected" : "" %>>Indisponível</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Alterar Status</button>
    </form>

    <a href="<%= request.getContextPath() %>/books" class="btn btn-secondary mt-3">Cancelar</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

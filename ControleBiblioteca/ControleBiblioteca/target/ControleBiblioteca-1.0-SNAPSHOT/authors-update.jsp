<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.models.Author" %>
<%@ page import="java.util.List" %>
<%
    Author author = (Author) request.getAttribute("author");
    List<Author> authors = (List<Author>) request.getAttribute("authors");
    String errorMessage = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Atualizar Autor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-4">
    <h1>Atualizar Autor</h1>

    <% if (errorMessage != null) { %>
    <div class="alert alert-danger">
        <%= errorMessage %>
    </div>
    <% } %>

    <form action="<%= request.getContextPath() %>/authors/update" method="post">
        <input type="hidden" name="id" value="<%= author.getId() %>">

        <div class="mb-3">
            <label for="name" class="form-label">Nome:</label>
            <input type="text" id="name" name="name" class="form-control" value="<%= author.getName() %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Atualizar</button>
        <a href="<%= request.getContextPath() %>/authors" class="btn btn-secondary">Cancelar</a>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

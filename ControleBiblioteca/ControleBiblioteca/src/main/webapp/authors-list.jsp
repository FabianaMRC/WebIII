<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.models.Author" %>
<%@ page import="java.util.List" %>
<%
    List<Author> authors = (List<Author>) request.getAttribute("authors");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Autores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
    <h1 class="mt-4">Lista de Autores</h1>
    <a class="btn btn-info" href="<%= request.getContextPath() %>/authors/create">Cadastrar Autor</a>
    <div class="table-responsive mt-3">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th>Id</th>
                <th>Nome</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <% if (authors != null && !authors.isEmpty()) { %>
            <% for (Author author : authors) { %>
            <tr>
                <td><%= author.getId() %></td>
                <td><%= author.getName() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/authors/update?id=<%= author.getId() %>" class="btn btn-sm btn-primary">Editar</a>
                    <a href="<%= request.getContextPath() %>/authors/delete?id=<%= author.getId() %>" class="btn btn-sm btn-danger">Excluir</a>
                </td>
            </tr>
            <% } %>
            <% } else { %>
            <tr>
                <td colspan="3">Nenhum autor cadastrado.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

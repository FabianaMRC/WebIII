<%@ page import="br.edu.ifpr.controlebiblioteca.models.Author" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Livros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="mb-4">Cadastrar Livro</h1>
    <form action="<%= request.getContextPath() %>/books/create" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Nome:</label>
            <input type="text" class="form-control" id="name" name="field_name">
        </div>

        <div class="mb-3">
            <label for="field_author" class="form-label">Autor:</label>
            <select class="form-select" id="field_author" name="field_author">
                <option value="" disabled selected>Selecione o autor</option>
                <%
                    List<Author> authors = (List<Author>) request.getAttribute("authors");
                    if (authors != null) {
                        for (Author author : authors) {
                %>
                <option value="<%= author.getId() %>"><%= author.getName() %></option>
                <%
                    }
                } else {
                %>
                <option value="" disabled>Nenhum autor cadastrado</option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <a href="<%= request.getContextPath() %>/authors/create" class="btn btn-secondary btn-sm">Cadastrar Novo Autor</a>
        </div>

        <div class="mb-3">
            <label for="date" class="form-label">Data de Criação:</label>
            <input type="date" class="form-control" id="date" name="field_date" placeholder="DD/MM/AAAA">
        </div>

        <div class="mb-3">
            <label for="status" class="form-label">Status:</label>
            <select class="form-select" id="status" name="field_status">
                <option value="DISPONIVEL">Disponível</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Cadastrar Livro</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

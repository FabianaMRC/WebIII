<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.models.Book" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.models.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.utils.DateUtils" %>
<%@ page import="br.edu.ifpr.controlebiblioteca.models.BookStatus" %>

<%
    List<Book> books = (List<Book>) request.getAttribute("books");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administração de Livros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<!-- Menu superior -->
<nav class="navbar navbar-expand-lg shadow-sm navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#" style="color: #006400;">
            <img src="images/logo.png" alt=""> Biblioteca IFPR
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto"></ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 main-content">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Livros Cadastrados</h1>
                <div>
                    <a class="btn btn-info me-2" href="<%= request.getContextPath() %>/books/create">Cadastrar Livro</a>
                    <a class="btn btn-secondary" href="<%= request.getContextPath() %>/authors/list">Listar Autores</a>
                </div>
            </div>

            <!-- Tabela de livros -->
            <div class="table-responsive">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Autor</th>
                        <th>Data de Criação</th>
                        <th>Status</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Book book : books) { %>
                    <tr>
                        <td><%= book.getId() %></td>
                        <td><%= book.getName() %></td>
                        <td><%= book.getAuthor().getName() %></td>
                        <td><%= DateUtils.localDateToBrazilianFormat(book.getDate()) %></td>
                        <td><%= book.getStatus() %></td>
                        <td>
                            <% if (book.getStatus().equals(BookStatus.INDISPONIVEL)) { %>
                            <a href="<%= request.getContextPath() %>/books/delete?id=<%= book.getId() %>" class="btn btn-sm btn-danger">Excluir</a>
                            <% } %>
                            <a href="<%= request.getContextPath() %>/books/update?id=<%= book.getId() %>" class="btn btn-sm btn-primary">Editar</a>
                            <a href="<%= request.getContextPath() %>/books/updateStatus?id=<%= book.getId() %>" class="btn btn-sm btn-warning">Alterar Status</a>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <!-- Regras de validação -->
            <div class="alert alert-info mt-3">
                <strong>Regras:</strong>
                <ul>
                    <li>Ao cadastrar um livro, o status só pode ser <strong>Disponível</strong>.</li>
                    <li>A exclusão de livros só é permitida se o status for <strong>Indisponível</strong>.</li>
                    <li>A exclusão de autores só é permitida quando não houver livros associados.</li>
                </ul>
            </div>
        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.querySelectorAll('form').forEach(form => {
        form.addEventListener('submit', function(event) {
            let valid = true;
            let fields = this.querySelectorAll('input[required], select[required]');
            fields.forEach(field => {
                if (!field.value) {
                    valid = false;
                    alert('Por favor, preencha todos os campos obrigatórios.');
                }
            });
            if (!valid) {
                event.preventDefault();
            }
        });
    });
</script>

</body>
</html>

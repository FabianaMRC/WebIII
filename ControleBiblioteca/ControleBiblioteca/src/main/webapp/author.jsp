<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro de Autor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
    <h1 class="mt-4">Cadastrar Autor</h1>
    <form action="<%= request.getContextPath() %>/authors/create" method="post">
        <div class="mb-3">
            <label for="author_name" class="form-label">Nome do Autor:</label>
            <input type="text" class="form-control" id="author_name" name="author_name" required>
        </div>

        <button type="submit" class="btn btn-primary">Cadastrar Autor</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


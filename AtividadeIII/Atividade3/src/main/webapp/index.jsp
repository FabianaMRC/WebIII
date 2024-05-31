<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Map<String, String> erros = (Map<String, String>) request.getAttribute("erros_map");
%>
<html>
<head>
    <title>Curriculum Vitae</title>

    <script src="https://cdn.tailwindcss.com"></script>
</head>
<style>
    .erro {
        color: #ff4b45;
        font-size: 18px;
        align-items: center;
    }

    .label {
        display: block;
        font-size: 15px;
        font-weight: bold;
        margin-bottom: 5px;
    }
</style>
<body>

<div class="isolate bg-white p-20">

<div class="mx-auto max-w-2xl text-center">
    <h1 class="text-2xl font-bold tracking-tight text-gray-900">Cadastro de Currículo</h1>
    <p class="mt-2 leading-8 text-gray-600">Aplicação para vagas de emprego na Islon Must</p>
</div>

<form action="<%= request.getContextPath()%>/curriculo-validacao" method="GET">

    <div class="mb-2">
        <label class="label">Nome Completo</label>
        <input type="text" name="nome"  placeholder="Digite o nome completo" class="block px-2.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 border rounded-md" style="width: 1200px;">
        <p class="erro"><% if (erros != null && erros.containsKey("erro_nome")) {out.print(erros.get("erro_nome"));}%></p>
    </div>

    <div class="mb-2">
    <label class="label">Data de nascimento</label>
        <input type="date" name="dtNascimento" class="block border-0 py-2 text-gray-900 shadow-sm"> </label>
        <p class="erro"><% if (erros != null && erros.containsKey("erro_dtNascimento")) {out.print(erros.get("erro_dtNascimento"));}%></p>
    </div>

    <div class="mb-2">
    <label class="label">Idioma Nativo</label>

        <input type="radio" name="idioma" value="espanhol"> Espanhol<br>
        <input type="radio" name="idioma" value="ingles"> Inglês<br>
        <input type="radio" name="idioma" value="portugues"> Português<br>
        <p class="erro"><% if (erros != null && erros.containsKey("erro_idioma")) {out.print(erros.get("erro_idioma"));}%></p>
    </div>

    <div class="mb-2">
        <label class="label">Habilidades técnicas</label>
        <input name="hbTecnicas" type="checkbox" value="java"> Java<br>
        <input name="hbTecnicas" type="checkbox" value="js"> JavaScript<br>
        <input name="hbTecnicas" type="checkbox" value="html"> HTML<br>
        <input name="hbTecnicas" type="checkbox" value="css"> CSS<br>
        <p class="erro"><% if (erros != null && erros.containsKey("erro_hbTecnicas")) {out.print(erros.get("erro_hbTecnicas"));}%></p>
    </div>

    <div class="mb-2">
    <label class="label">Informações adicionais</label>

        <textarea name="texto" placeholder="Utilize este espaço para fornecer informações importantes. Utilizar NSA para não se aplica" class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"></textarea>

        <p class="erro"><% if (erros != null && erros.containsKey("erro_texto")) {out.print(erros.get("erro_texto"));}%></p>
    </div>

    <div class="mb-2">
        <button type="submit" class="rounded-md bg-indigo-600 px-3 py-2 text-white hover:bg-indigo-500">Enviar</button>
    </div>

</form>
</div>
</body>
</html>
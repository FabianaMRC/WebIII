<%@ page import="java.util.Map" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
 Map<String, String> erros = (Map<String, String>) request.getAttribute("valida_erros");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>


  <style>
    .erro{
      color: #ff5445;
      font-size: 14px;
    }
  </style>
</head>
<body style="width:100%; justify-content: right">
<h1>Para acesso ao sistema, realizar o login </h1>
<br/>
<form action="<%= request.getContextPath()%>/login" method="post">
  <div class="erro"><% if (erros != null && erros.containsKey("erro_login")) {out.print("<br>" + erros.get("erro_login"));}%></div>
  <label>
    Nome de usuário:
    <br>
    <input type="text" name="usuario">
    <br><div class="erro"><% if (erros != null && erros.containsKey("erro_usuario")) {out.print(erros.get("erro_usuario"));}%></div>
  </label>
  <br>
  <label>
    Senha:
    <br>
    <input type="password" name="senha">
    <br><div class="erro"><% if (erros != null && erros.containsKey("erro_senha")) {out.print(erros.get("erro_senha"));}%></div>
  </label>
  <br>
  <br>
  <button type="submit">Login</button>
</form>
</body>
</html>
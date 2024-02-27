package br.edu.ifpr.atividade;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cabecalhos")

public class cabecalhosServlet extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        String host = req.getHeader("host");
        String cliente = req.getHeader("user-agent");
        String codificacao = req.getHeader("accept-encoding");
        String idioma = req.getHeader("accept-language");

        writer.println("<html");

        writer.println("<p>" + host + "</p>");
        writer.println("<p>" + cliente + "</p>");
        writer.println("<p>" + codificacao + "</p>");
        writer.println("<p>" + idioma + "</p>");

        writer.println("</html");



    }
}

package br.edu.ifpr.atividade3.controllers;

import br.edu.ifpr.atividade3.models.Curriculo;
import br.edu.ifpr.atividade3.services.CurService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "CurServlet", value = "/curriculo-validacao")

public class CurServlet extends HttpServlet {

    private String message;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Curriculo curriculo = new Curriculo();
        CurService service = new CurService();

        Map<String, String> erros = new HashMap<>();

        try {
            String nome = service.validaNome(request.getParameter("nome"));
            curriculo.setNome(nome);
        } catch (IllegalArgumentException e) {
            erros.put("erro_nome", e.getMessage());
        }

        try {
            LocalDate dtNascimento = service.validaNascimento(request.getParameter("dtNascimento"));
            curriculo.setDtNascimento(dtNascimento);
        } catch (IllegalArgumentException e) {
            erros.put("erro_dtNascimento", e.getMessage());
        }

        try {
            ArrayList<String> hbTecnicas = service.validaCheckbox(request.getParameterValues("hbTecnicas"));
            for (String ht: hbTecnicas ) {
                curriculo.setHabilidades(hbTecnicas);
            }
        } catch (IllegalArgumentException e){
            erros.put("erro_hbTecnicas", e.getMessage());
        }

        try {
            String texto = service.validaTexto(request.getParameter("texto"));
            curriculo.setTexto(texto);
        } catch (IllegalArgumentException e) {
            erros.put("erro_texto", e.getMessage());
        }

        try {
            String idioma = service.validaIdioma(request.getParameter("idioma"));
            curriculo.setIdioma(idioma);
        } catch (IllegalArgumentException e) {
            erros.put("erro_idioma", e.getMessage());
        }

        if (!erros.isEmpty()){
            request.setAttribute("erros_map", erros);
           RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }


        request.setAttribute("curriculo", curriculo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("sucesso.jsp");
        dispatcher.forward(request, response);

    }
}
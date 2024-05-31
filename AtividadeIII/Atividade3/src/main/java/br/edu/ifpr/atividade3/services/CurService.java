package br.edu.ifpr.atividade3.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CurService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final Set<String> idiomasDisponiveis = new HashSet<>(Arrays.asList("espanhol", "ingles", "portugues"));


    public LocalDate validaNascimento(String nascimento) {

        LocalDate dtNascimento;

        try {
            dtNascimento = LocalDate.parse(nascimento);

        } catch (DateTimeParseException e1) {
            try {
                dtNascimento = LocalDate.parse(nascimento, formatter);

            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Utilize o formato: Dia/Mês/Ano");
            }
        }
        return dtNascimento;
    }

    public String validaTexto(String texto) throws IllegalArgumentException {

        if (texto == null || texto.length() < 3) {
            throw new IllegalArgumentException("Campo Obrigatório");
        }
        return texto;
    }

    public ArrayList<String> validaCheckbox(String[] hbTecnicas) {

        ArrayList<String> list = new ArrayList<>();

        if (hbTecnicas == null) {
            throw new IllegalArgumentException("Favor especificar ao menos uma habilidade");
        }

        for (String s : hbTecnicas) {
            list.add(s.toUpperCase());
        }

        return list;
    }

    public String validaNome(String nome) throws IllegalArgumentException {

        if (nome == null || nome.length() < 5) {
            throw new IllegalArgumentException("O nome deve possuir no mínimo 5 caracteres");
        }
        return nome.trim();
    }

    public String validaIdioma(String idioma) {
        if (idioma == null || !idiomasDisponiveis.contains(idioma.toLowerCase())) {
            throw new IllegalArgumentException("Selecione um idioma");
        }
        return idioma.toLowerCase();
    }

}



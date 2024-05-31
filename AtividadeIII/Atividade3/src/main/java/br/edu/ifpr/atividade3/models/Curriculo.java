package br.edu.ifpr.atividade3.models;

import br.edu.ifpr.atividade3.services.CurService;

import java.time.LocalDate;
import java.util.ArrayList;

public class Curriculo {


    private String nome;
    private LocalDate dtNascimento;

    private String idioma;

    private ArrayList<String> habilidades = new ArrayList<>();

    private String texto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public ArrayList<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList<String> habilidades) {
        this.habilidades = habilidades;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}





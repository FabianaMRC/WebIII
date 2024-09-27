package br.edu.ifpr.controlebiblioteca.exceptions;

public class DataBaseIntegrity extends RuntimeException {
    public DataBaseIntegrity(String msg){
        super(msg);
    }
}

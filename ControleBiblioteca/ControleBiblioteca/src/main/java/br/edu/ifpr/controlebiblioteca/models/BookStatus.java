package br.edu.ifpr.controlebiblioteca.models;

public enum BookStatus {
    DISPONIVEL(1, "Disponível"),
    EMPRESTADO(2, "Emprestado"),
    INDISPONIVEL(3, "Indisponível");

    private final int id;
    private final String status;

    BookStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}

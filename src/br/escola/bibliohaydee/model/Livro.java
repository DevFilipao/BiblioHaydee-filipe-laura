package br.escola.bibliohaydee.model;

public class Livro {

    private String titulo;
    private String isbn;
    private Autor autor;
    private int anoPublicacao;
    private String genero;
    private boolean disponivel;

    public Livro(String titulo, String isbn, Autor autor,
                 int anoPublicacao, String genero) {

        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        this.disponivel = true;
    }

    // Construtor alternativo
    public Livro(String titulo, String isbn, Autor autor,
                 Integer anoDePublicacao) {

        this(titulo, isbn, autor, anoDePublicacao, "Não informado");
    }

    // Getters

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public Autor getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // Setters

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {

        return "\"" + titulo + "\""
                + " | ISBN: " + isbn
                + " | Autor: " + autor.getNome()
                + " | Ano: " + anoPublicacao
                + " | Gênero: " + genero
                + " | " + (disponivel ? "Disponível" : "Emprestado");
    }
}
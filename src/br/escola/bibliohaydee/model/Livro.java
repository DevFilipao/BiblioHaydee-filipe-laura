package br.escola.bibliohaydee.model;

public class Livro {
    String titulo;
    String isbn;
    Autor autor;          // referência a outro objeto
    int anoPublicacao;
    String genero;
   boolean disponivel;

    public Livro(String titulo, String isbn, Autor autor, int anoPublicacao, String genero) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.genero = genero;
        this.disponivel = true;
    }

    public Autor getAutor() { return autor; }
    public boolean isDisponivel() { return disponivel; }

    public String toString() {
        return "\"" + titulo + "\" | ISBN: " + isbn + " | Autor: " + autor.getNome()
                + " | " + anoPublicacao + " | " + genero
                + " | " + (disponivel ? "Disponível" : "Emprestado");
    }
}
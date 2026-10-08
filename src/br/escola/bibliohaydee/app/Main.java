package br.escola.bibliohaydee.app;

import br.escola.bibliohaydee.model.Autor;
import br.escola.bibliohaydee.model.Livro;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Autor> autores = new ArrayList<>();
    static List<Livro> acervo = new ArrayList<>();

    static Autor cadastrarAutor(String nome, String nacionalidade, int ano) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome do autor inválido. Cadastro cancelado.");
            return null;
        }
        Autor a = new Autor();
        a.setNome(nome.trim());
        a.setNacionalidade(nacionalidade);
        a.setAnoNascimento(ano);
        autores.add(a);
        return a;
    }

    static Livro cadastrarLivro(String titulo, String isbn, Autor autor, int ano, String genero) {
        if (autor == null || !autores.contains(autor)) {
            System.out.println("Autor não cadastrado. Cadastre ou selecione um autor antes.");
            return null;
        }
        Livro l = new Livro(titulo, isbn, autor, ano, genero);
        acervo.add(l);
        return l;
    }


    static void listarAcervo() {
        if (acervo.isEmpty()) {
            System.out.println("O acervo está vazio.");
            return;
        }
        for (Livro l : acervo) {
            System.out.println(l);
        }
    }


    public static void main(String[] args) {
        listarAcervo();


        cadastrarAutor("", "Brasileira", 1900);


        Autor machado = cadastrarAutor("Machado de Assis", "Brasileira", 1839);
        System.out.println(machado);


        cadastrarLivro("Dom Casmurro", "978-85-0000-000-1", null, 1899, "Romance");
        Livro dom = cadastrarLivro("Dom Casmurro", "978-85-0000-000-1", machado, 1899, "Romance");
        System.out.println("Disponível? " + dom.isDisponivel());


        listarAcervo();
    }
}

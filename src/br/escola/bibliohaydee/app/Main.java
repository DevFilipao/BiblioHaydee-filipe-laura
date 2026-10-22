package br.escola.bibliohaydee.app;

import br.escola.bibliohaydee.model.Autor;
import br.escola.bibliohaydee.model.Livro;
import br.escola.bibliohaydee.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Autor> autores = new ArrayList<>();
    static List<Livro> acervo = new ArrayList<>();
    static List<Usuario> usuarios = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    // ---------- leitura do teclado ----------

    static String lerTexto(String pergunta) {
        System.out.print(pergunta);
        return scanner.nextLine();
    }

    static int lerInteiro(String pergunta) {
        while (true) {
            System.out.print(pergunta);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    // ---------- cadastros ----------

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
        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("Título inválido. Cadastro cancelado.");
            return null;
        }
        Livro l = new Livro(titulo.trim(), isbn, autor, ano, genero);
        acervo.add(l);
        return l;
    }

    static Usuario cadastrarUsuario(String nome, String matricula, String tipo, int limite) {
        // A validação fica no Usuario; o Main só trata o erro e avisa o operador.
        try {
            Usuario u = new Usuario(nome, matricula, tipo, limite);
            usuarios.add(u);
            return u;
        } catch (IllegalArgumentException e) {
            System.out.println("Cadastro cancelado: " + e.getMessage());
            return null;
        }
    }

    // ---------- telas ----------

    static void telaAutor() {
        System.out.println("\n--- Novo autor ---");
        String nome = lerTexto("Nome: ");
        String nacionalidade = lerTexto("Nacionalidade: ");
        int ano = lerInteiro("Ano de nascimento: ");
        Autor a = cadastrarAutor(nome, nacionalidade, ano);
        if (a != null) {
            System.out.println("Autor cadastrado: " + a);
        }
    }

    static void telaLivro() {
        System.out.println("\n--- Novo livro ---");
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado. Cadastre um autor primeiro.");
            return;
        }
        listarAutores();
        int escolha = lerInteiro("Número do autor: ");
        if (escolha < 1 || escolha > autores.size()) {
            System.out.println("Autor inexistente. Cadastro cancelado.");
            return;
        }
        Autor autor = autores.get(escolha - 1);

        String titulo = lerTexto("Título: ");
        String isbn = lerTexto("ISBN: ");
        int ano = lerInteiro("Ano de publicação: ");
        String genero = lerTexto("Gênero: ");

        Livro l = cadastrarLivro(titulo, isbn, autor, ano, genero);
        if (l != null) {
            System.out.println("Livro cadastrado: " + l);
        }
    }

    static void telaUsuario() {
        System.out.println("\n--- Novo usuário ---");

        String nome;
        do {
            nome = lerTexto("Nome: ");
            if (!Usuario.nomeValido(nome)) {
                System.out.println("Nome inválido: informe um nome não vazio.");
            }
        } while (!Usuario.nomeValido(nome));

        String matricula;
        do {
            matricula = lerTexto("Matrícula: ");
            if (!Usuario.matriculaValida(matricula)) {
                System.out.println("Matrícula inválida: informe uma matrícula não vazia.");
            }
        } while (!Usuario.matriculaValida(matricula));

        String tipo;
        do {
            tipo = lerTexto("Tipo (ALUNO ou PROFESSOR): ");
            if (!Usuario.tipoValido(tipo)) {
                System.out.println("Tipo inválido: digite ALUNO ou PROFESSOR.");
            }
        } while (!Usuario.tipoValido(tipo));

        int limite;
        do {
            limite = lerInteiro("Limite de empréstimos: ");
            if (!Usuario.limiteValido(limite)) {
                System.out.println("Limite inválido: deve ser maior que zero.");
            }
        } while (!Usuario.limiteValido(limite));

        Usuario u = cadastrarUsuario(nome, matricula, tipo, limite);
        if (u != null) {
            System.out.println("Usuário cadastrado: " + u.getNome());
        }
    }

    // ---------- listagens ----------

    static void listarAutores() {
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }
        for (int i = 0; i < autores.size(); i++) {
            System.out.println((i + 1) + " - " + autores.get(i));
        }
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

    static void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    // ---------- programa principal ----------

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n===== BIBLIOTECA =====");
            System.out.println("1 - Cadastrar autor");
            System.out.println("2 - Cadastrar livro");
            System.out.println("3 - Cadastrar usuário");
            System.out.println("4 - Listar autores");
            System.out.println("5 - Listar acervo");
            System.out.println("6 - Listar usuários");
            System.out.println("0 - Sair");
            opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1: telaAutor(); break;
                case 2: telaLivro(); break;
                case 3: telaUsuario(); break;
                case 4: listarAutores(); break;
                case 5: listarAcervo(); break;
                case 6: listarUsuarios(); break;
                case 0: System.out.println("Até logo!"); break;
                default: System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
package br.escola.bibliohaydee.app;

import br.escola.bibliohaydee.model.Autor;
import br.escola.bibliohaydee.model.Livro;
import br.escola.bibliohaydee.model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class Main {

    static List<Autor> autores = new ArrayList<>();
    static List<Livro> acervo = new ArrayList<>();
    static List<Usuario> usuarios = new ArrayList<>();

    // =========================================
    // CADASTRO DE AUTORES
    // =========================================

    static Autor cadastrarAutor(String nome, String nacionalidade, int ano) {

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome do autor inválido.");
            return null;
        }

        Autor autor = new Autor();

        autor.setNome(nome.trim());
        autor.setNacionalidade(nacionalidade);
        autor.setAnoNascimento(ano);

        autores.add(autor);

        return autor;
    }

    // =========================================
    // CADASTRO DE LIVROS
    // =========================================

    static Livro cadastrarLivro(String titulo, String isbn,
                                Autor autor, int ano, String genero) {

        if (titulo == null || titulo.trim().isEmpty()) {
            System.out.println("Título inválido.");
            return null;
        }

        if (autor == null || !autores.contains(autor)) {
            System.out.println("Autor não cadastrado.");
            return null;
        }

        if (genero == null || genero.trim().isEmpty()) {
            genero = "Não informado";
        }

        Livro livro = new Livro(
                titulo.trim(),
                isbn,
                autor,
                ano,
                genero
        );

        acervo.add(livro);

        return livro;
    }

    // =========================================
    // CARREGAR ACERVO DE TESTE
    // =========================================

    static void carregarAcervoTeste() {

        if (!acervo.isEmpty()) {
            return;
        }

        Autor machado = cadastrarAutor(
                "Machado de Assis", "Brasileiro", 1839
        );

        Autor graciliano = cadastrarAutor(
                "Graciliano Ramos", "Brasileiro", 1892
        );

        Autor aluisio = cadastrarAutor(
                "Aluísio Azevedo", "Brasileiro", 1857
        );

        Autor jorge = cadastrarAutor(
                "Jorge Amado", "Brasileiro", 1912
        );

        cadastrarLivro(
                "Dom Casmurro", "1001",
                machado, 1899, "Romance"
        );

        cadastrarLivro(
                "Memórias Póstumas de Brás Cubas", "1002",
                machado, 1881, "Romance"
        );

        cadastrarLivro(
                "Quincas Borba", "1003",
                machado, 1891, "Romance"
        );

        cadastrarLivro(
                "Vidas Secas", "1004",
                graciliano, 1938, "Drama"
        );

        cadastrarLivro(
                "O Cortiço", "1005",
                aluisio, 1890, "Naturalismo"
        );

        cadastrarLivro(
                "Capitães da Areia", "1006",
                jorge, 1937, "Romance"
        );

        System.out.println("Acervo de teste carregado!");
    }

    // =========================================
    // CADASTRO DE AUTOR PELO CONSOLE
    // =========================================

    static void cadastrarAutorConsole(Scanner scanner) {

        System.out.print("Nome do autor: ");
        String nome = scanner.nextLine();

        System.out.print("Nacionalidade: ");
        String nacionalidade = scanner.nextLine();

        int ano = lerInteiro(scanner, "Ano de nascimento: ");

        Autor autor = cadastrarAutor(nome, nacionalidade, ano);

        if (autor != null) {
            System.out.println("Autor cadastrado com sucesso!");
        }
    }

    // =========================================
    // CADASTRO DE LIVRO PELO CONSOLE
    // =========================================

    static void cadastrarLivroConsole(Scanner scanner) {

        if (autores.isEmpty()) {
            System.out.println("Cadastre um autor primeiro.");
            return;
        }

        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.println("\n=== AUTORES CADASTRADOS ===");

        for (int i = 0; i < autores.size(); i++) {
            System.out.println(
                    (i + 1) + " - " + autores.get(i).getNome()
            );
        }

        int opcao = lerInteiro(scanner, "Escolha o autor: ");

        if (opcao < 1 || opcao > autores.size()) {
            System.out.println("Opção de autor inválida.");
            return;
        }

        Autor autor = autores.get(opcao - 1);

        int ano = lerInteiro(scanner, "Ano de publicação: ");

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        Livro livro = cadastrarLivro(
                titulo, isbn, autor, ano, genero
        );

        if (livro != null) {
            System.out.println("Livro cadastrado com sucesso!");
        }
    }

    // =========================================
    // LISTAGEM DO ACERVO
    // =========================================

    static void listarAcervo() {

        if (acervo.isEmpty()) {
            System.out.println("O acervo está vazio.");
            return;
        }

        System.out.println("\n=== ACERVO DE LIVROS ===");

        for (Livro livro : acervo) {
            System.out.println(livro);
        }

        System.out.println("\nTotal de livros: " + acervo.size());
    }

    // =========================================
    // PARTE 2 - PESQUISA POR TÍTULO (RF-006)
    // =========================================

    private static void pesquisarPorTitulo(ArrayList<Livro> livros) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nDigite parte do título: ");
        String termo = scanner.nextLine();

        // Validação do termo
        if (termo == null || termo.trim().isEmpty()) {
            System.out.println("Problema: informe um título válido.");
            return;
        }

        // Padronização
        termo = termo.trim().toLowerCase(Locale.ROOT);

        boolean encontrou = false;
        int quantidade = 0;

        // Percorrer o acervo e pesquisar
        for (Livro livro : livros) {

            String titulo = livro.getTitulo()
                    .trim()
                    .toLowerCase(Locale.ROOT);

            if (titulo.contains(termo)) {

                System.out.println(livro);

                encontrou = true;
                quantidade++;
            }
        }

        // Resultado
        if (!encontrou) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            System.out.println(
                    "Quantidade de livros encontrados: " + quantidade
            );
        }
    }

    // =========================================
    // PARTE 3 - PESQUISA POR AUTOR (RF-007)
    // =========================================

    private static void pesquisarPorAutor(ArrayList<Livro> livros) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nDigite o nome ou sobrenome do autor: ");
        String termo = scanner.nextLine();

        // Validação
        if (termo == null || termo.trim().isEmpty()) {
            System.out.println("Problema: informe um autor válido.");
            return;
        }

        // Padronização
        termo = termo.trim().toLowerCase(Locale.ROOT);

        boolean encontrou = false;
        int quantidade = 0;

        for (Livro livro : livros) {

            String nomeAutor = livro.getAutor()
                    .getNome()
                    .trim()
                    .toLowerCase(Locale.ROOT);

            // Comparação do nome completo
            boolean corresponde = nomeAutor.equals(termo);

            // Comparação das partes do nome usando split
            String[] partes = nomeAutor.split("\\s+");

            for (String parte : partes) {

                if (parte.equals(termo)) {
                    corresponde = true;
                    break;
                }
            }

            if (corresponde) {

                System.out.println(livro);

                encontrou = true;
                quantidade++;
            }
        }

        // Resultado
        if (!encontrou) {
            System.out.println("Autor não encontrado.");
        } else {
            System.out.println(
                    "Quantidade de livros encontrados: " + quantidade
            );
        }
    }

    // =========================================
    // PARTE 4 - RELATÓRIO DO ACERVO (RF-008)
    // =========================================

    private static void gerarRelatorio(ArrayList<Livro> livros) {

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("\n========== RELATÓRIO DO ACERVO ==========\n");

        if (livros.isEmpty()) {

            relatorio.append("Não há livros cadastrados no acervo.\n");

        } else {

            for (int i = 0; i < livros.size(); i++) {

                Livro livro = livros.get(i);

                relatorio.append("\nLivro nº ")
                        .append(i + 1)
                        .append("\nTítulo: ")
                        .append(livro.getTitulo())
                        .append("\nAutor: ")
                        .append(livro.getAutor().getNome())
                        .append("\nDisponibilidade: ")
                        .append(livro.isDisponivel()
                                ? "Disponível"
                                : "Emprestado")
                        .append("\n");
            }

            relatorio.append("\nTotal de livros: ")
                    .append(livros.size());
        }

        relatorio.append("\n=========================================");

        System.out.println(relatorio.toString());
    }

    // =========================================
    // CADASTRO DE USUÁRIOS
    // =========================================

    static Usuario buscarUsuario(String matricula) {

        for (Usuario usuario : usuarios) {

            if (usuario.getMatricula()
                    .equalsIgnoreCase(matricula.trim())) {

                return usuario;
            }
        }

        return null;
    }

    static Usuario cadastrarUsuario(String nome, String matricula,
                                    String tipo, Integer limite) {

        try {

            Usuario usuario;

            if (limite == null) {
                usuario = new Usuario(nome, matricula, tipo);
            } else {
                usuario = new Usuario(nome, matricula, tipo, limite);
            }

            for (Usuario cadastrado : usuarios) {

                if (cadastrado.getMatricula()
                        .equalsIgnoreCase(usuario.getMatricula())) {

                    System.out.println(
                            "Esta matrícula já está cadastrada."
                    );

                    return null;
                }
            }

            usuarios.add(usuario);

            System.out.println(
                    "Usuário cadastrado: " + usuario.getNome()
            );

            return usuario;

        } catch (IllegalArgumentException erro) {

            System.out.println("Erro no cadastro: " + erro.getMessage());

            return null;
        }
    }

    static void cadastrarUsuarioConsole(Scanner scanner) {

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        System.out.print("Tipo (ALUNO ou PROFESSOR): ");
        String tipo = scanner.nextLine();

        int limite = lerInteiro(scanner, "Limite de empréstimos (0 a 5): ");

        cadastrarUsuario(nome, matricula, tipo, limite);
    }

    // =========================================
    // ATUALIZAR NOME DO USUÁRIO
    // =========================================

    static void atualizarNome(Scanner scanner) {

        System.out.print("Matrícula do usuário: ");
        String matricula = scanner.nextLine().trim();

        Usuario usuario = buscarUsuario(matricula);

        if (usuario == null) {
            System.out.println("Usuário não localizado.");
            return;
        }

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        try {

            usuario.setNome(novoNome);

            System.out.println("Nome atualizado com sucesso!");

        } catch (IllegalArgumentException erro) {

            System.out.println("Erro: " + erro.getMessage());
        }
    }

    // =========================================
    // ATUALIZAR LIMITE DE EMPRÉSTIMOS
    // =========================================

    static void atualizarLimite(Scanner scanner) {

        System.out.print("Matrícula do usuário: ");
        String matricula = scanner.nextLine().trim();

        Usuario usuario = buscarUsuario(matricula);

        if (usuario == null) {
            System.out.println("Usuário não localizado.");
            return;
        }

        int limite = lerInteiro(scanner, "Novo limite (0 a 5): ");

        try {

            usuario.setLimiteEmprestimos(limite);

            System.out.println("Limite atualizado com sucesso!");

        } catch (IllegalArgumentException erro) {

            System.out.println("Erro: " + erro.getMessage());
        }
    }

    // =========================================
    // LISTAR USUÁRIOS
    // =========================================

    static void listarUsuarios() {

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.println("\n=== USUÁRIOS CADASTRADOS ===");

        for (Usuario usuario : usuarios) {

            System.out.println("\nNome: " + usuario.getNome());
            System.out.println("Matrícula: " + usuario.getMatricula());
            System.out.println("Tipo: " + usuario.getTipo());

            System.out.println(
                    "Limite de empréstimos: "
                            + usuario.getLimiteEmprestimos()
            );

            System.out.println(
                    "Empréstimos ativos: "
                            + usuario.getEmprestimosAtivos()
            );
        }
    }

    // =========================================
    // LEITURA DE NÚMEROS
    // =========================================

    static int lerInteiro(Scanner scanner, String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String entrada = scanner.nextLine();

            try {

                return Integer.parseInt(entrada.trim());

            } catch (NumberFormatException erro) {

                System.out.println("Digite um número válido.");
            }
        }
    }

    // =========================================
    // MENU PRINCIPAL
    // =========================================

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Carregar os seis livros de teste
        carregarAcervoTeste();

        int opcao;

        do {

            System.out.println("\n========== BIBLIOTECA HAYDEE ==========");

            System.out.println("1  - Cadastrar autor");
            System.out.println("2  - Cadastrar livro");
            System.out.println("3  - Listar acervo");
            System.out.println("4  - Pesquisar livro por título");
            System.out.println("5  - Pesquisar livro por autor");
            System.out.println("6  - Gerar relatório do acervo");
            System.out.println("7  - Cadastrar usuário");
            System.out.println("8  - Listar usuários");
            System.out.println("9  - Atualizar nome do usuário");
            System.out.println("10 - Atualizar limite de empréstimos");
            System.out.println("0  - Sair");

            System.out.println("=======================================");

            opcao = lerInteiro(scanner, "Escolha uma opção: ");

            switch (opcao) {

                case 1:
                    cadastrarAutorConsole(scanner);
                    break;

                case 2:
                    cadastrarLivroConsole(scanner);
                    break;

                case 3:
                    listarAcervo();
                    break;

                case 4:
                    pesquisarPorTitulo(new ArrayList<>(acervo));
                    break;

                case 5:
                    pesquisarPorAutor(new ArrayList<>(acervo));
                    break;

                case 6:
                    gerarRelatorio(new ArrayList<>(acervo));
                    break;

                case 7:
                    cadastrarUsuarioConsole(scanner);
                    break;

                case 8:
                    listarUsuarios();
                    break;

                case 9:
                    atualizarNome(scanner);
                    break;

                case 10:
                    atualizarLimite(scanner);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
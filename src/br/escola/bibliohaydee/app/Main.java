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


    // Cadastro de autores
    static Autor cadastrarAutor(String nome, String nacionalidade, int ano) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome do autor inválido. Cadastro cancelado.");
            return null;
        }


        Autor autor = new Autor();
        autor.setNome(nome.trim());
        autor.setNacionalidade(nacionalidade);
        autor.setAnoNascimento(ano);


        autores.add(autor);
        return autor;
    }

    static Usuario buscarUsuario(String matricula) {
        for (Usuario usuario : usuarios) {
            if (usuario.getMatricula().equalsIgnoreCase(matricula.trim())) {
                return usuario;
            }
        }

        return null;
    }

    // Cadastro de livros
    static Livro cadastrarLivro(String titulo, String isbn, Autor autor,
                                int ano, String genero) {
        if (autor == null || !autores.contains(autor)) {
            System.out.println(
                    "Autor não cadastrado. Cadastre ou selecione um autor antes.");
            return null;
        }

        if (genero == null || genero.trim().isEmpty()) {
            genero = "Não informado";
        }

        Livro livro = new Livro(titulo, isbn, autor, ano, genero);
        acervo.add(livro);
        return livro;
    }


    // Listagem do acervo
    static void listarAcervo() {
        if (acervo.isEmpty()) {
            System.out.println("O acervo está vazio.");
            return;
        }


        for (Livro livro : acervo) {
            System.out.println(livro);
        }
    }


    // Cadastro de usuários
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
                if (cadastrado.getMatricula().equalsIgnoreCase(
                        usuario.getMatricula())) {
                    System.out.println("Esta matrícula já está cadastrada.");
                    return null;
                }
            }

            usuarios.add(usuario);


            System.out.println(
                    "Usuário cadastrado: " + usuario.getNome());


            return usuario;


        } catch (IllegalArgumentException erro) {
            System.out.println("Erro no cadastro: " + erro.getMessage());
            return null;
        }
    }


    // Entrada de dados do usuário pelo console
    static void cadastrarUsuarioConsole(Scanner scanner) {
        String nome;


        do {
            System.out.print("Nome: ");
            nome = scanner.nextLine();


            if (!Usuario.nomeValido(nome)) {
                System.out.println(
                        "Nome inválido. Informe um nome não vazio.");
            }
        } while (!Usuario.nomeValido(nome));


        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();


        String tipo;


        do {
            System.out.print("Tipo (ALUNO ou PROFESSOR): ");
            tipo = scanner.nextLine();


            if (!Usuario.tipoValido(tipo)) {
                System.out.println(
                        "Tipo inválido. Digite ALUNO ou PROFESSOR.");
            }
        } while (!Usuario.tipoValido(tipo));


        int limite;


        while (true) {
            System.out.print("Limite de empréstimos: ");
            String entrada = scanner.nextLine();


            try {
                limite = Integer.parseInt(entrada);

                if (limite < 0 || limite > 5) {
                    System.out.println(
                            "O limite deve estar entre 0 e 5."
                    );
                    continue;
                }

                break;

            } catch (NumberFormatException erro) {
                System.out.println("Digite um número válido.");
            }
        }


        cadastrarUsuario(nome, matricula, tipo, limite);
    }

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

    static void atualizarLimite(Scanner scanner) {
        System.out.print("Matrícula do usuário: ");
        String matricula = scanner.nextLine().trim();

        Usuario usuario = buscarUsuario(matricula);

        if (usuario == null) {
            System.out.println("Usuário não localizado.");
            return;
        }

        while (true) {
            System.out.print("Novo limite de empréstimos: ");
            String entrada = scanner.nextLine().trim();

            try {
                int limite = Integer.parseInt(entrada);
                usuario.setLimiteEmprestimos(limite);

                System.out.println("Limite atualizado com sucesso!");
                break;

            } catch (NumberFormatException erro) {
                System.out.println("Digite um número válido.");
            } catch (IllegalArgumentException erro) {
                System.out.println("Erro: " + erro.getMessage());
            }

        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String resposta;
        do {
            System.out.println("\n=== CADASTRO DE USUÁRIO ===");
            cadastrarUsuarioConsole(scanner);


            System.out.print("\nDeseja cadastrar outro usuário? (sim/não): ");
            resposta = scanner.nextLine();


        } while (resposta.equalsIgnoreCase("sim"));


        // Conferência dos usuários cadastrados
        System.out.println("\n=== USUÁRIOS CADASTRADOS ===");


        for (Usuario usuario : usuarios) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Matrícula: " + usuario.getMatricula());
            System.out.println("Tipo: " + usuario.getTipo());
            System.out.println(
                    "Limite de empréstimos: "
                            + usuario.getLimiteEmprestimos());
            System.out.println(
                    "Empréstimos ativos: "
                            + usuario.getEmprestimosAtivos());
        }

        scanner.close();
    }
}


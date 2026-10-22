package br.escola.bibliohaydee.model;

import java.util.Scanner;

public class Usuario {
    private String nome;
    private String matricula;
    private String tipo;
    private Integer limiteEmprestimos;
    private Integer emprestimosAtivos;

    public Usuario(String nomeInformado, String matriculaInformada,
                   String tipoInformado, Integer limiteInformado) {
        if (!nomeValido(nomeInformado)) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (!tipoValido(tipoInformado)) {
            throw new IllegalArgumentException("O tipo deve ser ALUNO ou PROFESSOR.");
        }
        nome = nomeInformado.trim();
        matricula = matriculaInformada;
        tipo = tipoInformado.trim().toUpperCase();
        limiteEmprestimos = limiteInformado;
        emprestimosAtivos = 0;
    }


    public static boolean nomeValido(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }

    public static boolean tipoValido(String valor) {
        if (valor == null) {
            return false;
        }
        String t = valor.trim().toUpperCase();
        return t.equals("ALUNO") || t.equals("PROFESSOR");
    }

    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public String getTipo() { return tipo; }
    public Integer getLimiteEmprestimos() { return limiteEmprestimos; }
    public Integer getEmprestimosAtivos() { return emprestimosAtivos; }

    public void setNome(String novoNome) {
        if (!nomeValido(novoNome)) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        nome = novoNome.trim();

        String nome;
        Scanner scanner = null;
        do {
            System.out.print("Nome: ");
            nome = scanner.nextLine();
            if (!Usuario.nomeValido(nome)) {
                System.out.println("Nome inválido: informe um nome não vazio.");
            }
        } while (!Usuario.nomeValido(nome));

        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        String tipo;
        do {
            System.out.print("Tipo (ALUNO ou PROFESSOR): ");
            tipo = scanner.nextLine();
            if (!Usuario.tipoValido(tipo)) {
                System.out.println("Tipo inválido: digite ALUNO ou PROFESSOR.");
            }
        } while (!Usuario.tipoValido(tipo));

        System.out.print("Limite de empréstimos: ");
        Integer limite = Integer.parseInt(scanner.nextLine());

        Usuario usuario = new Usuario(nome, matricula, tipo, limite);
        Usuario.add(usuario); // use a mesma estrutura em memória que já existe no Main
        System.out.println("Usuário cadastrado: " + usuario.getNome());
    }

    private static void add(Usuario usuario) {

    }
}



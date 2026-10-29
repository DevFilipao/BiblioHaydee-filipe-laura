package br.escola.bibliohaydee.model;


public class Usuario {


    private String nome;
    private String matricula;
    private String tipo;
    private Integer limiteEmprestimos;
    private Integer emprestimosAtivos;


    public Usuario(String nomeInformado, String matriculaInformada,
                   String tipoInformado, Integer limiteInformado) {


        if (!nomeValido(nomeInformado)) {
            throw new IllegalArgumentException(
                    "O nome não pode ser nulo ou vazio.");
        }


        if (matriculaInformada == null ||
                matriculaInformada.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "A matrícula não pode ser vazia.");
        }


        if (!tipoValido(tipoInformado)) {
            throw new IllegalArgumentException(
                    "O tipo deve ser ALUNO ou PROFESSOR.");
        }


        if (limiteInformado == null || limiteInformado < 0) {
            throw new IllegalArgumentException(
                    "O limite de empréstimos deve ser zero ou maior.");
        }


        nome = nomeInformado.trim();
        matricula = matriculaInformada.trim();
        tipo = tipoInformado.trim().toUpperCase();
        limiteEmprestimos = limiteInformado;
        emprestimosAtivos = 0;
    }

    public void Livro(String titulo, String isbn, Autor autor, Integer anoDePublicacao) {

    }

    public static final int LIMITE_PADRAO = 3;

    public Usuario(String nome, String matricula, String tipo) {

    }


    public static boolean nomeValido(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }


    public static boolean tipoValido(String valor) {
        if (valor == null) {
            return false;
        }


        String tipoNormalizado = valor.trim().toUpperCase();


        return tipoNormalizado.equals("ALUNO")
                || tipoNormalizado.equals("PROFESSOR");
    }


    public String getNome() {
        return nome;
    }


    public String getMatricula() {
        return matricula;
    }


    public String getTipo() {
        return tipo;
    }


    public Integer getLimiteEmprestimos() {
        return limiteEmprestimos;
    }


    public Integer getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public boolean setNome(String novoNome) {
        if (!nomeValido(novoNome)) {
            throw new IllegalArgumentException(
                    "O nome não pode ser nulo ou vazio.");
        }

        nome = novoNome.trim();
        return false;
    }

    public void setTipo(String novoTipo) {
        if (!tipoValido(novoTipo)) {
            throw new IllegalArgumentException(
                    "O tipo deve ser ALUNO ou PROFESSOR.");
        }


        tipo = novoTipo.trim().toUpperCase();
    }



    public boolean setLimiteEmprestimos(Integer novoLimite) {
        if (novoLimite == null || novoLimite < 0) {
            throw new IllegalArgumentException(
                    "O limite deve ser zero ou maior.");
        }

        limiteEmprestimos = novoLimite;


        return false;
    }
}

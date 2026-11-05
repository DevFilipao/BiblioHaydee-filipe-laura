package br.escola.bibliohaydee.model;

public class Usuario {

    private String nome;
    private String matricula;
    private String tipo;
    private Integer limiteEmprestimos;
    private Integer emprestimosAtivos;

    public static final int LIMITE_PADRAO = 3;

    public Usuario(String nomeInformado, String matriculaInformada,
                   String tipoInformado, Integer limiteInformado) {

        if (!nomeValido(nomeInformado)) {
            throw new IllegalArgumentException(
                    "O nome não pode ser nulo ou vazio."
            );
        }

        if (matriculaInformada == null ||
                matriculaInformada.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "A matrícula não pode ser vazia."
            );
        }

        if (!tipoValido(tipoInformado)) {
            throw new IllegalArgumentException(
                    "O tipo deve ser ALUNO ou PROFESSOR."
            );
        }

        if (limiteInformado == null ||
                limiteInformado < 0 || limiteInformado > 5) {

            throw new IllegalArgumentException(
                    "O limite deve estar entre 0 e 5."
            );
        }

        nome = nomeInformado.trim();
        matricula = matriculaInformada.trim();
        tipo = tipoInformado.trim().toUpperCase();
        limiteEmprestimos = limiteInformado;
        emprestimosAtivos = 0;
    }

    public Usuario(String nome, String matricula, String tipo) {
        this(nome, matricula, tipo, LIMITE_PADRAO);
    }

    // Validações

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

    // Getters

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

    // Setters

    public void setNome(String novoNome) {

        if (!nomeValido(novoNome)) {
            throw new IllegalArgumentException(
                    "O nome não pode ser vazio."
            );
        }

        nome = novoNome.trim();
    }

    public void setTipo(String novoTipo) {

        if (!tipoValido(novoTipo)) {
            throw new IllegalArgumentException(
                    "O tipo deve ser ALUNO ou PROFESSOR."
            );
        }

        tipo = novoTipo.trim().toUpperCase();
    }

    public void setLimiteEmprestimos(int novoLimite) {

        if (novoLimite < 0 || novoLimite > 5) {
            throw new IllegalArgumentException(
                    "O limite deve estar entre 0 e 5."
            );
        }

        limiteEmprestimos = novoLimite;
    }
}
package br.escola.bibliohaydee.model;

public class Usuario {
    private String nome;
    private String matricula;
    private String tipo;
    private Integer limiteEmprestimos;
    private Integer emprestimosAtivos;

    public Usuario(String nome, String matricula, String tipo, Integer limiteEmprestimos) {
        if (!nomeValido(nome)) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (!matriculaValida(matricula)) {
            throw new IllegalArgumentException("A matrícula não pode ser nula ou vazia.");
        }
        if (!tipoValido(tipo)) {
            throw new IllegalArgumentException("O tipo deve ser ALUNO ou PROFESSOR.");
        }
        if (!limiteValido(limiteEmprestimos)) {
            throw new IllegalArgumentException("O limite de empréstimos deve ser maior que zero.");
        }
        this.nome = nome.trim();
        this.matricula = matricula.trim();
        this.tipo = tipo.trim().toUpperCase();
        this.limiteEmprestimos = limiteEmprestimos;
        this.emprestimosAtivos = 0;
    }

    public static boolean nomeValido(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }

    public static boolean matriculaValida(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }

    public static boolean tipoValido(String valor) {
        if (valor == null) {
            return false;
        }
        String t = valor.trim().toUpperCase();
        return t.equals("ALUNO") || t.equals("PROFESSOR");
    }

    public static boolean limiteValido(Integer valor) {
        return valor != null && valor > 0;
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
    }

    @Override
    public String toString() {
        return nome + " | Matrícula: " + matricula + " | " + tipo
                + " | Limite: " + limiteEmprestimos
                + " | Empréstimos ativos: " + emprestimosAtivos;
    }
}
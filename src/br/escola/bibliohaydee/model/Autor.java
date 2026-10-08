package br.escola.bibliohaydee.model;

public class Autor {
    String nome;
    String nacionalidade;
    int anoNascimento;

    public Autor() { }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    public int getAnoNascimento() { return anoNascimento; }
    public void setAnoNascimento(int anoNascimento) { this.anoNascimento = anoNascimento; }

    public String toString() {
        return nome + " (" + nacionalidade + ", nascido em " + anoNascimento + ")";
    }
}
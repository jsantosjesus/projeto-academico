package br.com.academico.pessoa;

import java.io.Serializable;

import br.com.academico.endereco.Endereco;

public abstract class Pessoa implements Serializable {
    int matricula;
    protected String nome;
    protected String sobrenome;
    protected int idade;
    protected String naturalidade;
    protected String sexo;
    protected String cpf;

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa() {
    }

    public Pessoa(int matricula, String nome, String sobrenome, int idade, String naturalidade, String sexo,
            String cpf) {
        this.matricula = matricula;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.naturalidade = naturalidade;
        this.sexo = sexo;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "Matrícula: " + this.getMatricula() + " \n";
        detalhes += "Nome Completo: " + this.getNomeCompleto() + " \n";
        detalhes += "Idade: " + this.getIdade() + " \n";
        detalhes += "Naturalidade: " + this.getNaturalidade() + " \n";
        detalhes += "Sexo: " + this.getSexo() + " \n";
        detalhes += "CPF: " + this.getCpf() + " \n";
        return detalhes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pessoa) {
            Pessoa pessoa = (Pessoa) obj;
            return this.matricula == pessoa.getMatricula();
        }
        return false;
    }

    public abstract int gerarMatricula();
}
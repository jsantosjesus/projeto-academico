package br.com.academico.professor;

import java.io.Serializable;
import java.util.Random;

import br.com.academico.endereco.Endereco;
import br.com.academico.evento.Ieventos;
import br.com.academico.pessoa.Pessoa;
import br.com.academico.projeto.Iprojetos;

public class Professor extends Pessoa implements Iprojetos, Ieventos, Serializable {
    private int id;

    private float salario;
    private int cargaHoraria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor() {
        super();
        this.setMatricula(this.gerarMatricula());
    }

    public Professor(float salario, int cargaHoraria) {
        super();
        this.salario = salario;
        this.cargaHoraria = cargaHoraria;
    }

    public Professor(int matricula, String nome, String sobrenome, int idade, String naturalidade, String sexo,
            String cpf, float salario, int cargaHoraria) {
        super(matricula, nome, sobrenome, idade, naturalidade, sexo, cpf);
        this.salario = salario;
        this.cargaHoraria = cargaHoraria;
    }

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += super.toString();
        detalhes += "Salário: " + this.getSalario() + "\n";
        detalhes += "Carga Horária: " + this.getCargaHoraria() + "\n";
        return detalhes;
    }

    @Override
    public int gerarMatricula() {

        Random gerador = new Random();
        int min = 10000000;
        int max = 99999999;
        return gerador.nextInt(max - min + 1) + min;
    }

    @Override
    public void submeterProjetosDeExtensao() {
        System.out.println("1- Professor escolhe a area de atuação da extensão");
        System.out.println("2- Professor escreve o projeto de extensão");
        System.out.println("3- Professor seleciona alunos para o projeto, se necessário");
        System.out.println("4- Projeto é avaliado pela setor de extensão da instituição");

    }

    @Override
    public void submeterProjetosDePesquisa() {
        System.out.println("1- Professor escolhe a area de pesquisa");

        System.out.println("2- Professor escreve o projeto de pesquisa");
        System.out.println("3- Professor apresenta documentação para concorrer ao financiamento da pesquisa");

        System.out.println("4- Professor seleciona alunos para o projeto, se necessário");
        System.out.println("5- Projeto é avaliado pela setor de pesquisa da instituição");
    }

    @Override
    public void inscrever() {
        System.out.println("1 -Inscrição de professor no evento");

        System.out.println("2- Gerar boleto de pagamento para o Professor");

    }
}

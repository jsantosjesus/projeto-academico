package br.com.academico.aluno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import br.com.academico.endereco.Endereco;
import br.com.academico.evento.Ieventos;
import br.com.academico.pessoa.Pessoa;
import br.com.academico.projeto.Iprojetos;

public class Aluno extends Pessoa implements Iprojetos, Ieventos, Serializable {

    private int id;
    private String curso;
    private boolean estaMatriculado;
    private double media;
    private double ponderada;
    private boolean aprovado;

    String situacaoAluno;

    List<Nota> notas = new ArrayList<Nota>();

    static int quantidadeAlunos = 0;
    static String nomeInstituicao = "IFS";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public boolean isEstaMatriculado() {
        return estaMatriculado;
    }

    public void setEstaMatriculado(boolean estaMatriculado) {
        this.estaMatriculado = estaMatriculado;
    }

    public static int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public static void setQuantidadeAlunos(int quantidadeAlunos) {
        Aluno.quantidadeAlunos = quantidadeAlunos;
    }

    public static String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public static void setNomeInstituicao(String nomeInstituicao) {
        Aluno.nomeInstituicao = nomeInstituicao;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public double getPonderada() {
        return ponderada;
    }

    public void setPonderada(double ponderada) {
        this.ponderada = ponderada;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public String getSituacaoAluno() {
        return situacaoAluno;
    }

    public void setSituacaoAluno(String situacaoAluno) {
        this.situacaoAluno = situacaoAluno;
    }

    // Construtores
    public Aluno() {
        super();
        this.incrementaQuantidadeDeAlunos();
        this.setMatricula(this.gerarMatricula());
    }

    public Aluno(int matricula, String nome, String sobrenome, int idade, String naturalidade, String sexo,
            String cpf, String curso, boolean estaMatriculado, double media, double ponderada, boolean aprovado,
            String situacaoAluno) {
        super(matricula, nome, sobrenome, idade, naturalidade, sexo, cpf);
        this.curso = curso;
        this.estaMatriculado = estaMatriculado;
        this.incrementaQuantidadeDeAlunos();
        this.setMatricula(this.gerarMatricula());
        this.media = media;
        this.ponderada = ponderada;
        this.aprovado = aprovado;
        this.situacaoAluno = situacaoAluno;
    }

    // Métodos

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += super.toString();
        detalhes += "Curso: " + this.getCurso() + " \n";
        detalhes += "Esta Matriculado? " + this.isEstaMatriculado() + " \n";
        detalhes += this.getNotas() + " \n";
        detalhes += "Média: " + this.obterMedia() + "\n";
        detalhes += "Média ponderada: " + this.obterPonderada() + "\n";
        detalhes += "Situação: " + this.situacao() + "\n";
        return detalhes;
    }

    private void incrementaQuantidadeDeAlunos() {
        ++Aluno.quantidadeAlunos;
    }

    @Override
    public int gerarMatricula() {

        Random gerador = new Random();
        Calendar calendario = Calendar.getInstance();
        int ano = calendario.get(Calendar.YEAR);
        int min = 1000;
        int max = 9999;
        String matricula = String.valueOf(ano) + String.valueOf(gerador.nextInt(max - min + 1) + min);
        return Integer.parseInt(matricula);
    }

    public double obterMedia() {
        double soma = 0;
        for (Nota nota : notas) {
            soma += nota.getValor();
        }
        media = soma / notas.size();
        this.setMedia(media);
        return media;

    }

    public double obterPonderada() {
        double mutiplicacao = 0;
        double somaPeso = 0;
        for (Nota nota : notas) {
            mutiplicacao += nota.getPeso() * nota.getValor();
            somaPeso += nota.getPeso();
        }
        media = mutiplicacao / somaPeso;
        return media;
    }

    private String situacao() {
        if (this.obterMedia() >= 7) {
            this.setSituacaoAluno(SituacaoAluno.Aprovado.toString());
            this.setAprovado(true);
        }

        else if (this.obterMedia() >= 5 && this.obterMedia() < 7) {
            this.setSituacaoAluno(SituacaoAluno.Recuperacao.toString());
            this.setAprovado(false);
        } else {
            this.setSituacaoAluno(SituacaoAluno.Reprovado.toString());
            this.setAprovado(false);
        }
        return situacaoAluno;
    }

    @Override
    public void submeterProjetosDeExtensao() {
        System.out.println("1- Aluno escolhe a area de atuação da extensão");
        System.out.println("2- Aluno escreve o projeto de extensão");
        System.out.println("3- O projeto é avaliado por professores");
    }

    @Override
    public void submeterProjetosDePesquisa() {
        System.out.println("1- Aluno escolhe o tema da pesquisa");

        System.out.println("2- Aluno escreve o projeto de pesquisa");
        System.out.println("3- Projeto é avaliado por professores");
        System.out.println("4- Aluno apresenta documentação para concorrer ao financiamento da pesquisa");
    }

    @Override
    public void inscrever() {
        System.out.println("1- Inscrição de aluno no evento");
    }
}

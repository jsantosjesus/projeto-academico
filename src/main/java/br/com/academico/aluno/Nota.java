package br.com.academico.aluno;

import java.io.Serializable;

public class Nota implements Serializable {

    private int id;
    private double valor;
    private int peso;
    private int matricula;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Nota() {

    }

    public Nota(double valor, int peso) {
        this.valor = valor;
        this.peso = peso;
    }

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "Nota: " + this.valor + "\n";
        detalhes += "Peso: " + this.peso + "\n";

        return detalhes;
    }

}

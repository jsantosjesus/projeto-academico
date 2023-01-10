package br.com.academico.sala;

import java.io.Serializable;

public class Sala implements Serializable {

    private int id;
    private int numero;
    private int capacidade;
    private boolean laboratorio;
    private boolean possuiQuadroBranco;
    private boolean possuiArcondicionado;

    // gets e sets

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(boolean laboratorio) {
        this.laboratorio = laboratorio;
    }

    public boolean isPossuiQuadroBranco() {
        return possuiQuadroBranco;
    }

    public void setPossuiQuadroBranco(boolean possuiQuadroBranco) {
        this.possuiQuadroBranco = possuiQuadroBranco;
    }

    public boolean isPossuiArcondicionado() {
        return possuiArcondicionado;
    }

    public void setPossuiArcondicionado(boolean possuiArcondicionado) {
        this.possuiArcondicionado = possuiArcondicionado;
    }

    // construtores

    public Sala() {

    }

    public Sala(int numero, int capacidade, boolean laboratorio, boolean possuiQuadroBranco,
            boolean possuiArcondicionado) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.laboratorio = laboratorio;
        this.possuiQuadroBranco = possuiQuadroBranco;
        this.possuiArcondicionado = possuiArcondicionado;
    }

    // toString

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "\n";
        detalhes += "Número: " + this.getNumero() + "\n";
        detalhes += "Capacidade: " + this.getCapacidade() + "\n";
        detalhes += "Possui ArCondicionado?: " + this.isPossuiArcondicionado() + "\n";
        detalhes += "Possui Quadro branco?: " + this.isPossuiQuadroBranco() + "\n";
        detalhes += "É laboratório?: " + this.isLaboratorio() + "\n";
        return detalhes;
    }

}

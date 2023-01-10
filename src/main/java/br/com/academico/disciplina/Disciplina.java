package br.com.academico.disciplina;

import java.io.Serializable;
import javax.validation.constraints.Pattern;

public class Disciplina implements Serializable {
    private int Id;
    @Pattern(regexp="[0-9]{4}-[A-Z]*", message = "O atributo nome da disciplina é inválido.")
    private String nome;
    private int cargaHoraria;

    // gets e sets
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    // construtores

    public Disciplina() {

    }

    public Disciplina(String nome, int cargaHoraria) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    // toString

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "\n";
        detalhes += "Nome: " + this.getNome() + "\n";
        detalhes += "Carga Horária: " + this.getCargaHoraria() + "\n";
        return detalhes;
    }

}

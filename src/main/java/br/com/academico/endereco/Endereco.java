package br.com.academico.endereco;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class Endereco implements Serializable {
    private int id;
    @Range(min=10000000, max=99999999,  message = "O atributo CEP deve ser inteiro e ter no mínimo 8 algarismos.")
    int cep;
    @Size(min=5, max=50,  message = "O atributo Rua deve conter no mínimo 5 e no máxima 50 caracteres.")
    @NotEmpty(message = "O atributo rua não pode ser vazio.")
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private int numero;
    private StatusEndereco status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public StatusEndereco getStatus() {
        return status;
    }

    public void setStatus(StatusEndereco status) {
        this.status = status;
    }

    public Endereco() {
        this.status = StatusEndereco.ATIVO;
    }

    public Endereco(int cep, String rua, String bairro, String cidade, String estado, int numero) {
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.status = StatusEndereco.ATIVO;
    }

    @Override
    public String toString() {
        String detalhes = "";
        detalhes += "------------------------- \n";
        detalhes += "ENDEREÇO \n";
        detalhes += "Id: " + this.id + "\n";
        detalhes += "CEP: " + this.cep + "\n";
        detalhes += "Estado: " + this.estado + "\n";
        detalhes += "Cidade: " + this.cidade + "\n";
        detalhes += "Bairro: " + this.bairro + "\n";
        detalhes += "Rua: " + this.rua + "\n";
        detalhes += "Número: " + this.numero + "\n";
        detalhes += "Status: " + this.status + "\n";
        return detalhes;
    }

}

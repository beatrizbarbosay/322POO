package model;

import java.io.Serializable;

// Representa um piloto com informações pessoais.
public class Piloto implements Serializable {
    private String nome;
    private int idade;
    private String nacionalidade;
    private Sexo sexo;

    public Piloto(String nome, int idade, String nacionalidade, Sexo sexo) {
        this.nome = nome;
        this.idade = idade;
        this.nacionalidade = nacionalidade;
        this.sexo = sexo;
    }

    // Métodos de acesso.
    public String getNome() {
        return nome;
    }
    public int getIdade() {
        return idade;
    }
    public String getNacionalidade() {
        return nacionalidade;
    }
    public Sexo getSexo() {
        return sexo;
    }

    @Override
    // Formato: "Nome | Sexo: X | Idade: Y anos | Nacionalidade: Z".
    public String toString() {
        return String.format(
            "%s | Sexo: %s | Idade: %d anos | Nacionalidade: %s",
            nome, sexo, idade, nacionalidade
        );
    }

}
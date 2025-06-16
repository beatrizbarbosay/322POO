package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Representa um piloto com informações pessoais.
public class Piloto implements Serializable {
    private String nome;
    private int idade;
    private String nacionalidade;
    private Sexo sexo;
    private double nivel;
    private List<Double> desempenhos;

    public Piloto(String nome, int idade, String nacionalidade, Sexo sexo) {
        this.nome = nome;
        this.idade = idade;
        this.nacionalidade = nacionalidade;
        this.sexo = sexo;
        this.nivel = 70; // Nível inicial
        this.desempenhos = new ArrayList<>();
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
    public double getNivel() {
        return nivel;
    }
    public List<Double> getDesempenhos() {
        return new ArrayList<>(desempenhos);
    }

    public void atualizarNivel(double desempenhoCorrida) {
        this.desempenhos.add(desempenhoCorrida);
        double somaPonderada = 0;
        double pesoTotal = 0;
        
        // Dá mais peso às corridas recentes
        for (int i = 0; i < desempenhos.size(); i++) {
            double peso = Math.pow(1.1, i); // Fator de crescimento exponencial
            somaPonderada += desempenhos.get(i) * peso;
            pesoTotal += peso;
        }
        
        // Garante nível entre 30 e 100
        this.nivel = Math.max(30, Math.min(100, (somaPonderada / pesoTotal) * 100));
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
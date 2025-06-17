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
        // Garante que a lista de desempenhos não seja nula após desserialização
        if (this.desempenhos == null) {
            this.desempenhos = new ArrayList<>();
        }
        
        this.desempenhos.add(desempenhoCorrida);
        calcularNivel(); // Recalcula o nível com base em TODOS os desempenhos
        BancoPilotos.adicionarPiloto(this); // Força a persistência do piloto atualizado
    }

    private void calcularNivel() {
        if (desempenhos == null || desempenhos.isEmpty()) {
            this.nivel = 70; // Valor padrão se não houver histórico
            return;
        }

        double somaPonderada = 0;
        double pesoTotal = 0;
        
        for (int i = 0; i < desempenhos.size(); i++) {
            double peso = Math.pow(1.1, i);
            somaPonderada += desempenhos.get(i) * peso;
            pesoTotal += peso;
        }
        
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
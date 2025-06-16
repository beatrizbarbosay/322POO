package model;

import javafx.beans.property.*;

// Classe para exibir estatísticas de pilotos em tabelas JavaFX.
public class PilotoEstatistica {
    private final StringProperty nome;
    private final IntegerProperty idade;
    private final StringProperty nacionalidade;
    private final DoubleProperty nivel;

    public PilotoEstatistica(String nome, int idade, String nacionalidade, double nivel) {
        this.nome = new SimpleStringProperty(nome);
        this.idade = new SimpleIntegerProperty(idade);
        this.nacionalidade = new SimpleStringProperty(nacionalidade);
        this.nivel = new SimpleDoubleProperty(nivel);
    }

    // Getters para propriedades (usados em bindings JavaFX).
    public StringProperty nomeProperty() {
        return nome;
    }
    public IntegerProperty idadeProperty() {
        return idade;
    }
    public StringProperty nacionalidadeProperty() {
        return nacionalidade;
    }
    public DoubleProperty nivelProperty() {
        return nivel;
    }

    // Getters convencionais.
    public String getNome() {
        return nome.get();
    }
    public int getIdade() {
        return idade.get();
    }
    public String getNacionalidade() {
        return nacionalidade.get();
    }
    public double getNivel() {
        return nivel.get();
    }
}
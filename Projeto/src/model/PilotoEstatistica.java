package model;

import javafx.beans.property.*;

public class PilotoEstatistica {
    private final StringProperty nome;
    private final IntegerProperty idade;
    private final StringProperty nacionalidade;
    private final DoubleProperty tempoMedio;

    public PilotoEstatistica(String nome, int idade, String nacionalidade, double tempoMedio) {
        this.nome = new SimpleStringProperty(nome);
        this.idade = new SimpleIntegerProperty(idade);
        this.nacionalidade = new SimpleStringProperty(nacionalidade);
        this.tempoMedio = new SimpleDoubleProperty(tempoMedio);
    }

    // Getters para as propriedades
    public StringProperty nomeProperty() { return nome; }
    public IntegerProperty idadeProperty() { return idade; }
    public StringProperty nacionalidadeProperty() { return nacionalidade; }
    public DoubleProperty tempoMedioProperty() { return tempoMedio; }

    // Getters convencionais
    public String getNome() { return nome.get(); }
    public int getIdade() { return idade.get(); }
    public String getNacionalidade() { return nacionalidade.get(); }
    public double getTempoMedio() { return tempoMedio.get(); }
}
package model;

import java.io.Serializable;

// Classe abstrata que representa um carro genérico.
public abstract class Carro implements Serializable {
    protected String modelo;  // Modelo do carro (ex: "Fusca").
    protected double velocidadeMax;  // Em km/h.

    public Carro(String modelo, double velocidadeMax) {
        this.modelo = modelo;
        this.velocidadeMax = velocidadeMax;
    }

    // Métodos de acesso.
    public String getModelo() {
        return modelo;
    }
    public double getvelocidadeMax() {
        return velocidadeMax;
    }

    // Retorna o tipo específico do carro (implementado pelas subclasses).
    public abstract String getTipo();

    @Override
    // Formato: "Modelo [Tipo] - VelocidadeMáxima: X km/h".
    public String toString() {
        return modelo + " [" + getTipo() + "]" + " - VelocidadeMáxima:" + velocidadeMax + "Km/h";
    }
}
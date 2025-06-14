package model;

// Representa um carro popular (subclasse de Carro).
public class CarroPopular extends Carro {
    public CarroPopular(String modelo, double velocidadeMax) {
        super(modelo, velocidadeMax);
    }

    @Override
    public String getTipo() {
        return "Carro Popular";
    }
}